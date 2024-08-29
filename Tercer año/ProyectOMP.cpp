Q[0] = new double[N * D];
for (size_t i = 1; i < N; ++i) {
Q[i] = Q[i - 1] + D;
}
#pragma omp parallel for collapse(2) schedule(static) if(N > 200)
for (size_t i = 0; i < N; ++i) {
for (size_t j = 0; j < D; ++j) {
double producto_escalar = 0.0;
for (size_t k = 0; k < D; ++k) {
if (i < N && j < N && k < D) {
producto_escalar += WQT[j][k] * X[i][k];
}
}
producto_escalar += BQ[j];
Q[i][j] = producto_escalar;
}
}
//Total=((2*D)*D)*N = 2D^2*N
//=====================================================================
double** A = new double*[N];
A[0] = new double[N * N];
for (size_t i = 1; i < N; ++i) {
A[i] = A[i - 1] + N;
}
#pragma omp parallel for collapse(2) schedule(static) if(N > 200)
for (size_t n = 0; n < N; ++n) {
for (size_t i = 0; i < N; ++i) {
double puntuacion = 0.0;
for (size_t d = 0; d < D; ++d) {
puntuacion += Q[n][d] * K[i][d]; //1+1=2
}
puntuacion /= sqrt(D); //4+8=12
A[n][i] = puntuacion;
}
}
//Total=((2*D)*12N)*N = 2D*12*N^2
//=====================================================================
double** ALFAS = new double*[N];
ALFAS[0] = new double[N * N];
for (size_t i = 1; i < N; ++i) {
ALFAS[i] = ALFAS[i - 1] + N;
}
#pragma omp parallel for collapse(1) schedule(static) if(N > 200)
for (size_t i = 0; i < N; ++i) {
double suma = 0.0;
for (size_t j = 0; j < N; ++j) {
suma += exp(A[i][j]);
}
for (size_t j = 0; j < N; ++j) {
ALFAS[i][j] = exp(A[i][j]) / suma;
}
}
//Total=((9*N)+(12*N))*N = 21*N^2
//=====================================================================
double** resultado = new double*[N];
resultado[0] = new double[N * D];
for (size_t i = 1; i < N; ++i) {
resultado[i] = resultado[i - 1] + D;
}
// Calcular la matriz resultado
#pragma omp parallel for collapse(2) schedule(static) if(N > 200)
for (size_t i = 0; i < N; ++i) {
for (size_t j = 0; j < D; ++j) {
double suma = 0.0;
for (size_t k = 0; k < N; ++k) {
suma += ALFAS[i][k] * V[k][j]; //1+1=2
}
resultado[i][j] = suma;
}
}
//Total=2N*D*N = 2*N^2*D
//=====================================================================
double t1 = get_time();
printf("Tiempo: %f s", t1 - t0);
cout << endl;
// Liberar memoria
std::cout << "\nLiberando espacio de los vectores" << std::endl;
liberarVector(BK);
liberarVector(BQ);
liberarVector(BV);
std::cout << "Liberando espacio de matrices\n" << std::endl;
liberarMatriz(X, D);
liberarMatriz(WK, D);
liberarMatriz(WQ, D);
liberarMatriz(WV, D);
}
void liberarMatriz(double** matriz, size_t filas) {
delete[] matriz[0];
delete[] matriz;
}
void liberarVector(double* vector) {
delete[] vector;
}
double* generarVectorAleatorio(size_t tamano) {
srand(time(nullptr));
double* vectorAleatorio = new double[tamano];
for (size_t i = 0; i < tamano; ++i) {
vectorAleatorio[i] = 0.001 * ((rand() / (float)RAND_MAX) - 0.5);
}
return vectorAleatorio;
}
double** calcularTranspuestaDinamica(double** matriz, size_t filas, size_t columnas) {
double** transpuesta = new double*[columnas];
transpuesta[0] = new double[filas * columnas];
for (size_t i = 1; i < columnas; ++i) {
transpuesta[i] = transpuesta[i - 1] + filas;
}
for (size_t i = 0; i < filas; ++i) {
for (size_t j = 0; j < columnas; ++j) {
transpuesta[j][i] = matriz[i][j];
}
}
return transpuesta;
}
Matriz calcularTranspuesta(const Matriz& matriz) {
size_t filas = matriz.size();
size_t columnas = matriz[0].size();
Matriz transpuesta(columnas, std::vector<double>(filas));
for (size_t i = 0; i < filas; ++i) {
for (size_t j = 0; j < columnas; ++j) {
transpuesta[j][i] = matriz[i][j];
}
}
return transpuesta;
}
void MostrarMatriz(vector<vector<double>> matriz)
{
for(size_t i = 0; i < matriz.size(); i++)
{
for(size_t j = 0; j < matriz[i].size(); j++)
{
printf("%.1f ", matriz[i][j]);
}
cout << endl;
}
}
Matriz AjusteLineal(const Matriz& X, const Matriz& WK, const Vector& bK) {
Matriz claves;
for (size_t i = 0; i < X.size(); ++i) {
Vector clave_i;
for (size_t j = 0; j < WK.size(); ++j) {
double producto_escalar = 0.0;
for (size_t k = 0; k < X[i].size(); ++k) {
producto_escalar += WK[j][k] * X[i][k];
}
producto_escalar += bK[j];
clave_i.push_back(producto_escalar);
}
claves.push_back(clave_i);
}
return claves;
}
Matriz calcularPuntuacionesAtencion(const Matriz& Q, const Matriz& K) {
Matriz A;
for (size_t n = 0; n < Q.size(); ++n) {
Vector puntuaciones_n;
for (size_t i = 0; i < K.size(); ++i) {
double puntuacion = 0.0;
for (size_t d = 0; d < Q[n].size(); ++d) {
puntuacion += Q[n][d] * K[i][d];
}
puntuacion /= sqrt(Q[n].size());
puntuaciones_n.push_back(puntuacion);
}
A.push_back(puntuaciones_n);
}
return A;
}
Matriz normalizarPuntuacionesAtencion(Matriz puntuaciones) {
Matriz puntuacionesNormalizadas;
for (const auto& fila : puntuaciones) {
Vector filaNormalizada;
double suma = 0.0;
for (double puntuacion : fila) {
suma += exp(puntuacion);
}
for (double puntuacion : fila) {
filaNormalizada.push_back(exp(puntuacion) / suma);
}
puntuacionesNormalizadas.push_back(filaNormalizada);
}
return puntuacionesNormalizadas;
}
Matriz calcularMatrizResultado(Matriz alfas, Matriz valores) {
Matriz resultado;
for (size_t i = 0; i < alfas.size(); ++i) {
Vector filaResultado;
for (size_t j = 0; j < valores[0].size(); ++j) {
double suma = 0.0;
for (size_t k = 0; k < valores.size(); ++k) {
suma += alfas[i][k] * valores[k][j];
}
filaResultado.push_back(suma);
}
resultado.push_back(filaResultado);
}
return resultado;
}
double** inicializarMatriz(int filas, int columnas) {
double* matrizContigua = new double[filas * columnas];
double** matriz = new double*[filas];
for (int i = 0; i < filas; ++i) {
matriz[i] = &matrizContigua[i * columnas];
for (int j = 0; j < columnas; ++j) {
matriz[i][j] = (rand() % 11); // Genera un número aleatorio entre 0 y 10
}
}
return matriz;
}
double ** inicializarParametros(int filas, int columnas) {
double* matrizContigua = new double[filas * columnas];
double** matriz = new double*[filas];
for (int i = 0; i < filas; ++i) {
matriz[i] = &matrizContigua[i * columnas];
for (int j = 0; j < columnas; ++j) {
matriz[i][j] = 0.001 * ((rand() / (float)RAND_MAX) - 0.5);// Genera un número aleatorio entre 0
y 10
}
}
return matriz;
}
