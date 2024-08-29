#include<vector>
#include <iostream>
#include <ctime>
#include <cmath>
#include <iomanip>
#include <mpi.h>
using namespace std;



typedef std::vector<std::vector<double>> Matriz;
typedef std::vector<double> Vector;




void MostrarMatriz(Matriz);
Matriz calcularTranspuesta(const Matriz& matriz) ;
void ModoDepuracion();
void ModoExperimentacion(double ** X,int N, int D,int,int);
Matriz AjusteLineal(const Matriz& X, const Matriz& WK, const Vector& bK);
Matriz calcularPuntuacionesAtencion(const Matriz& Q, const Matriz& K);
Matriz normalizarPuntuacionesAtencion(Matriz puntuaciones);
Matriz calcularMatrizResultado(Matriz alfas, Matriz valores);
double** inicializarMatriz(int filas, int columnas);
double ** inicializarParametros(int filas, int columnas);
void Algoritmo(double ** X, size_t N,size_t D,int,int);
double **calcularTranspuestaDinamica(double** matriz, size_t filas, size_t columnas) ;
double* generarVectorAleatorio(size_t tamano) ;

void mostrarVector(double* vector, int longitud);
void liberarVector(double* vector);
void liberarMatriz(double** matriz, size_t filas);
void mostrarMatrizPunteros(double** matriz, int filas, int columnas);

int main(int argc,char *argv[])
{
	int rank,  size;
	MPI_Init(&argc,&argv);
	MPI_Comm_rank(MPI_COMM_WORLD,&rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);
	double ** X = nullptr;
	size_t N,D;
	int opcion;
	do{	
		if(rank == 0)
		{
			cout << "1-Modo depuracion\n2-Modo experimentacion\n0-Salir\n";
			cin >> opcion;
		}
		MPI_Bcast(&opcion,1,MPI_INT,0,MPI_COMM_WORLD);		
		if(opcion == 1){
 	        	ModoDepuracion();
 	    	}else if (opcion == 2){
			if(rank == 0){
 	    			cout << "Valor de N " << endl;
 	    			cin >> N;
 	    			cout << "Valor de D " << endl;
 	    			cin >> D;
 	        		  X = inicializarMatriz(N,D);
 	        	
			}
			
				MPI_Bcast(&N, 1, MPI_INT, 0, MPI_COMM_WORLD);
                MPI_Bcast(&D, 1, MPI_INT, 0, MPI_COMM_WORLD);
                
                //==============Esta parte inicializa para cada proceso (no el 0)
				// la matriz X y envia la matriz por filas =================
                if(rank !=0)
                {
                	double* matrizContigua = new double[N * D];
                		X = new double*[N];
                		for (int i = 0; i < N; ++i) {
    						X[i] = &matrizContigua[i * D];
 			            }     
				}
                
                for (int i = 0; i < N; ++i) {
        			MPI_Bcast(X[i], D, MPI_DOUBLE, 0, MPI_COMM_WORLD);
				}
				//========================================================================
    		
                
		
			   
			 		    
 	    	ModoExperimentacion(X,N,D,rank,size);
               
	}
 	   
	}while(opcion !=0);

	MPI_Finalize();
	return 0;
}

void mostrarMatrizPunteros(double** matriz, int filas, int columnas) {
    for (int i = 0; i < filas; ++i) {
        for (int j = 0; j < columnas; ++j) {
            printf("%.5f ", matriz[i][j]);
        }
        std::cout << std::endl;
    }
}


void ModoExperimentacion(double ** X, int N, int D, int rank,int cantidad){

	 Algoritmo(X,N,D,rank,cantidad);

}

double get_time(){
 struct timespec t;
 clock_gettime ( CLOCK_REALTIME, &t );
 return (double) t.tv_sec + ((double)t.tv_nsec)/1e9;
 }

void ModoDepuracion()
{
	Matriz WK = {
    {-0.2, -0.1, 0.0, 0.1},
    {-0.2, -0.1, 0.0, 0.1},
    {-0.2, -0.1, 0.0, 0.1},
    {-0.2, -0.1, 0.0, 0.1}
};
Vector BK = {-1.0, -1.0, -1.0, -1.0};	
Matriz WQ = {
    {-0.2, -0.2, -0.2, -0.2},
    {-0.1, -0.1, -0.1, -0.1},
    { 0.0,  0.0,  0.0,  0.0},
    { 0.1,  0.1,  0.1,  0.1}
};	
Vector BQ = {0.1, 0.1, 0.1, 0.1};	

Matriz WV = {
    {1.0, 0.0, 0.0, 0.0},
    {0.0, 1.0, 0.0, 0.0},
    {0.0, 0.0, 1.0, 0.0},
    {0.0, 0.0, 0.0, 1.0}
};

Vector BV = {0.0, 0.0, 0.0, 0.0};	

Matriz X = {
    {0, 6, 12, 18},
    {1, 7, 13, 19},
    {2, 8, 14, 20},
    {3, 9, 15, 21},
    {4, 10, 16, 22},
    {5, 11, 17, 23}
};

Matriz WKT = calcularTranspuesta(WK);
Matriz WVT = calcularTranspuesta(WV);
Matriz WQT = calcularTranspuesta(WQ);

Matriz K =  AjusteLineal(X,WKT,BK);
Matriz V = AjusteLineal(X,WVT,BV);
Matriz Q = AjusteLineal(X,WQT,BQ);

Matriz A = calcularPuntuacionesAtencion(Q,K);
Matriz ALFAS = normalizarPuntuacionesAtencion(A);
Matriz c = calcularMatrizResultado(ALFAS,V);
MostrarMatriz(c);

}

void Algoritmo(double** X, size_t N, size_t D,int rank, int size) {
	//cout << "soy el proceso "<< rank << " hay: "<< cantidad<< endl;
	
	double** WK  = new double*[D*D];
    double** WKT = new double*[D];
    double* BK = new double[D];
    double** K = new double*[N];
    double** V = new double*[N];
    double** Q = new double*[N];
    double** WV = new double*[D*D];
    double** WVT = new double*[D];
    double* BV = new double[D];  
    double** WQ = new double*[D*D]; 
    double** WQT = new double*[D]; 
    double* BQ = new double[D]; 
    double** A = new double*[N];
    double** ALFAS = new double*[N];
    double** resultado = new double*[N];
     
	
	//bucle 1:  WKT, X ,BK,K
	
	if(rank == 0)
	{
		WK = inicializarParametros(D, D);
		WKT = calcularTranspuestaDinamica(WK, D, D);
		BK = generarVectorAleatorio(D);
        K[0] = new double[N * D];
   		
        for (size_t i = 1; i < N; ++i) {
   	 		K[i] = K[i - 1] + D;
		}
		
		 V[0] = new double[N * D];
		 for (size_t i = 1; i < N; ++i) {
   	     	 V[i] = V[i - 1] + D;
		    }
		    
        Q[0] = new double[N * D];
    
	    for (size_t i = 1; i < N; ++i) {
	    	Q[i] = Q[i - 1] + D;
		}	
         WV = inicializarParametros(D, D);
         WVT = calcularTranspuestaDinamica(WV, D, D);
         BV = generarVectorAleatorio(D);
         WQ = inicializarParametros(D, D);
         WQT = calcularTranspuestaDinamica(WQ, D, D);
         BQ = generarVectorAleatorio(D); 
		
	}
	else
	{
	
		WKT = new double*[D];
		WVT = new double*[D];
		WQT = new double*[D];
		WKT[0] = new double  [N*D];
		WVT[0] = new double  [N*D];
		WQT[0] = new double  [N*D];
		 for (int i = 1; i < D; ++i) {
         	 WKT[i] = WKT[i-1]+N;
    		 }
        
         for (int i = 1; i < D; ++i) {
         	 WVT[i] = WVT[i-1]+N;
		 }
        
         for (int i = 1; i < D; ++i) {
         	 WQT[i] = WQT[i-1]+N;
    		 }
		
		 K[0] = new double[N * D];
   		
        for (size_t i = 1; i < N; ++i) {
   	 		K[i] = K[i - 1] + D;
		}
		
		 V[0] = new double[N * D];
		 for (size_t i = 1; i < N; ++i) {
   	     	 V[i] = V[i - 1] + D;
		    }
	   Q[0] = new double[N * D];
    
	    for (size_t i = 1; i < N; ++i) {
	    	Q[i] = Q[i - 1] + D;
		}	
	}
	
	A[0] = new double[N * N]; //cambiar a N*N
	for (size_t i = 1; i < N; ++i) {
    	A[i] = A[i - 1] + N;
	}
	
	ALFAS[0] = new double[N * N];
	for (size_t i = 1; i < N; ++i) {
    	ALFAS[i] = ALFAS[i - 1] + N;
	}
	
	resultado[0] = new double[N * D];
	for (size_t i = 1; i < N; ++i) {
    	resultado[i] = resultado[i - 1] + D;
	}
	

	
	for (int i = 0; i < D; ++i) {
	      MPI_Bcast(WKT[i], D, MPI_DOUBLE, 0, MPI_COMM_WORLD);
	}
	
	for (int i = 0; i < D; ++i) {
	      MPI_Bcast(WVT[i], D, MPI_DOUBLE, 0, MPI_COMM_WORLD);
	}
	for (int i = 0; i < D; ++i) {
	      MPI_Bcast(WQT[i], D, MPI_DOUBLE, 0, MPI_COMM_WORLD);
	}

    MPI_Bcast(BK, D, MPI_DOUBLE, 0, MPI_COMM_WORLD);
    MPI_Bcast(BV, D, MPI_DOUBLE, 0, MPI_COMM_WORLD);
    MPI_Bcast(BQ, D, MPI_DOUBLE, 0, MPI_COMM_WORLD);
 
 	double t0 = get_time();

	 
	 //==========================================================================
	 double* tempK = new double[(N/size)*D];
	 
	for (size_t i = rank*(N/size); i < (rank+1)*(N/size); ++i) {//cambiar a N/SIZE
    	for (size_t j = 0; j < D; ++j) {
        	double producto_escalar = 0.0;
        	for (size_t k = 0; k < D; ++k) {
            	producto_escalar += WKT[j][k] * X[i][k];	//1+1=2
        	}
        	
        	
    		producto_escalar += BK[j];						//1
    		tempK[(i-rank*(N/size))*D+j] = producto_escalar;
    }
	}
		//Total=((2*D)*D)*N = 2D^2*N
		
         MPI_Gather(tempK , (N/size) * D, MPI_DOUBLE, K[0], (N/size) * D, MPI_DOUBLE, 0, MPI_COMM_WORLD);
         for (int i = 0; i < N; ++i) {
		 	 MPI_Bcast(K[i], D, MPI_DOUBLE, 0, MPI_COMM_WORLD);
 	   }
 	   
	 //==========================================================================
   double* tempV = new double[(N/size)*D];
   
    //=====================================================================

		for (size_t i = rank*(N/size); i < (rank+1)*(N/size); ++i) {
    		for (size_t j = 0; j < D; ++j) {
        		double producto_escalar = 0.0;
        		for (size_t k = 0; k < D; ++k) {
            		producto_escalar += WVT[j][k] * X[i][k];//1+1=2
        		}
        		producto_escalar += BV[j];					//1
        		tempV[(i-rank*(N/size))*D+j] = producto_escalar;
        	
   			}
		}//	//Total=((2*D)*D)*N = 2D^2*N
		
    MPI_Gather(tempV , (N/size) * D, MPI_DOUBLE, V[0], (N/size) * D, MPI_DOUBLE, 0, MPI_COMM_WORLD);
     for (int i = 0; i < N; ++i) {
		 	 MPI_Bcast(V[i], D, MPI_DOUBLE, 0, MPI_COMM_WORLD);
	 }
	
   
   


    //====================================================================
   
       double* tempQ = new double[(N/size)*D];


 	for (size_t i = rank*(N/size); i < (rank+1)*(N/size); ++i) {
    	for (size_t j = 0; j < D; ++j) {
        	double producto_escalar = 0.0;
        	for (size_t k = 0; k < D; ++k) {
            	if (i < N && j < N && k < D) {
                   producto_escalar += WQT[j][k] * X[i][k];	//1+1=2
	            }
	        }
	      
            producto_escalar += BQ[j];						//1
	        tempQ[(i-rank*(N/size))*D+j] = producto_escalar;
	    }
	}
	//Total=((2*D)*D)*N = 2D^2*N
	
	MPI_Gather(tempQ , (N/size) * D, MPI_DOUBLE, Q[0], (N/size) * D, MPI_DOUBLE, 0, MPI_COMM_WORLD);
	for (int i = 0; i < N; ++i) {
		 	 MPI_Bcast(Q[i], D, MPI_DOUBLE, 0, MPI_COMM_WORLD);
	 }
	 

	
 //=====================================================================
 
   
 	double* tempA = new double[(N/size)*N]; //* N y no por D
 	int pos = 0;
 	
 	for (size_t n = rank*(N/size); n < (rank+1)*(N/size); ++n) {
    	for (size_t i = 0; i < N; ++i) {
        	double puntuacion = 0.0;
        	for (size_t d = 0; d < D; ++d) {
            	   puntuacion += Q[n][d] * K[i][d];
				  			  	 		   		   	//1+1=2
        	}
        puntuacion /= sqrt(D);	
		
		if(pos < ((N/size)*N))	 //Cambiar a *N						//4+8=12
           tempA[pos] = puntuacion;
	   pos++;
	    }
	  
	}


	MPI_Gather(tempA , (N/size) * N, MPI_DOUBLE, A[0], (N/size) * N, MPI_DOUBLE, 0, MPI_COMM_WORLD); //cambiar a *N
	 for (int i = 0; i < N; ++i) {
		 	 MPI_Bcast(A[i], N, MPI_DOUBLE, 0, MPI_COMM_WORLD); // cambiar a N y quitar D
	 }
	//Total=((2*D)*12N)*N = 2D*12*N^2
	
	
     //=====================================================================
	 int pos_aux = 0;
	double* tempALFA = new double[(N/size)*N];  //LO MISMO QUE ARRIBA
		for (size_t i = rank*(N/size); i < (rank+1)*(N/size); ++i) 
		{
    	 	double suma = 0.0;
			for (size_t j = 0; j < N; ++j) {
        		suma += exp(A[i][j]);							//1+8=9
			}	
    		for (size_t j = 0; j < D; ++j) {
    			   if(pos_aux < ((N/size)*N))
		   			  tempALFA[pos_aux] = exp(A[i][j]) / suma;				//8+4=12
	    		   pos_aux++;
	    		
		}
	}	
	MPI_Gather(tempALFA , (N/size) * N, MPI_DOUBLE, ALFAS[0], (N/size) * N, MPI_DOUBLE, 0, MPI_COMM_WORLD);
	 for (int i = 0; i < N; ++i) {
		 	 MPI_Bcast(ALFAS[i], N, MPI_DOUBLE, 0, MPI_COMM_WORLD);
	 }
	
	//Total=((9*N)+(12*N))*N = 21*N^2
	 //=====================================================================
	  
 	double* tempRes = new double[(N/size)*D];

	int poslol = 0;
    for (size_t i = rank*(N/size); i < (rank+1)*(N/size); ++i) {
    	for (size_t j = 0; j < D; ++j) {
        	double suma = 0.0;
        	for (size_t k = 0; k < N; ++k) {
                   suma += ALFAS[i][k] * V[k][j];			//1+1=2	   
    		}
        	tempRes[(i-rank*(N/size))*D+j] = suma;
        	poslol++;
	    }
	}

		MPI_Gather(tempRes , (N/size) * D, MPI_DOUBLE, resultado[0], (N/size) * D, MPI_DOUBLE, 0, MPI_COMM_WORLD);
	 	


  if(rank == 0)
  {
  
    double t1 = get_time();
    printf("Tiempo: %f s", t1 - t0);
    cout << endl;
 }

    // Liberar memoria
   
    liberarVector(BK);
    liberarVector(BQ);
    liberarVector(BV);
   
  
    liberarMatriz(X, D);
    liberarMatriz(WK, D);
    liberarMatriz(WQ, D);
    liberarMatriz(WV, D);
    
    if(rank == 0)
    {
	
     std::cout << "\nLiberando espacio de los vectores" << std::endl;
     std::cout << "Liberando espacio de matrices\n" << std::endl;
   }

}

void mostrarVector(double* vector, int longitud) {
    for (size_t i = 0; i < longitud; ++i) {
        std::cout << vector[i] << " ";
    }
    std::cout << std::endl;
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
               printf("%.5f ", matriz[i][j]);
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
            matriz[i][j] = 0.001 * ((rand() / (float)RAND_MAX) - 0.5);// Genera un número aleatorio entre 0 y 10
        }
    }

  
    return matriz;
}





