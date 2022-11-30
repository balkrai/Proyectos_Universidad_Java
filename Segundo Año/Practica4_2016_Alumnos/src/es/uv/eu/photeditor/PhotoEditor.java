package es.uv.eu.photeditor;

//import es.uv.eu.photoeditor.controller.PhotoEditorController;
import es.uv.eu.photoeditor.controller.PhotoEditorController;
import es.uv.eu.photoeditor.model.PhotoEditorModel;
import es.uv.eu.photoeditor.view.PhotoEditorView;

/**
 *
 * @author diaz
 */
public class PhotoEditor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PhotoEditorModel model = new PhotoEditorModel();
        PhotoEditorView view = new PhotoEditorView(model);
        view.setSize(800,600);
        view.setVisible(true);
        PhotoEditorController controlador = new PhotoEditorController(view, model);
    }
}
