/*package src.backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public enum EffectType {
    LIGHTENED {
        @Override
        public Figure applyEffect(Figure fig){
            Figure aux = fig.copy();
        }
    }, DARKENED{
        @Override
        public void aplicar(Figura f, GraphicsContext gc) {
            gc.setFill(Color.rgb(0, 0, 0, 0.3));
            gc.fillRect(f.x, f.y, f.ancho, f.alto);
        }
    }, VMIRROR{
        @Override
        public void aplicar(Figura f, GraphicsContext gc) {
            gc.setFill(f.colorRelleno);
            if (f instanceof Rectangulo)
                gc.fillRect(f.x, f.y + f.alto * 2, f.ancho, f.alto);
            else
                gc.fillOval(f.x, f.y + f.alto * 2, f.ancho, f.alto);
        }
    }, HMIRROR{
        @Override
        public void aplicar(Figura f, GraphicsContext gc) {
            gc.setFill(f.colorRelleno);
            if (f instanceof Rectangulo)
                gc.fillRect(f.x + f.ancho * 2, f.y, f.ancho, f.alto);
            else
                gc.fillOval(f.x + f.ancho * 2, f.y, f.ancho, f.alto);
        }
    };

    public abstract void aplicar(Figura f, GraphicsContext gc);
    public abstract Figure applyEffect(Figure fig);


}*/
