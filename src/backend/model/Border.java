package src.backend.model;
import src.backend.DrawingContext;

/*
 ** Enum que representa el tipo de borde de la figura, ya sea
 ** 'Normal', 'Pixelado', 'Punteado Fino' o 'Punteado Complejo'.
 */
public enum Border{
    NORMAL("Normal"){
        @Override
        public void apply(DrawingContext dc) {
            dc.setLineWidth(1);
            dc.setLineDashes();
        }
    },

    PIXELEADO("Pixelado"){
        @Override
        public void apply(DrawingContext dc){
            dc.setLineWidth(5);
            dc.setLineCap(LineCap.BUTT);
            dc.setLineDashes(1, 1);
        }
    },

    PUNTEADO_FINO("Punteado Fino"){
        @Override
        public void apply(DrawingContext dc){
            dc.setLineWidth(1);
            dc.setLineCap(LineCap.ROUND);
            dc.setLineDashes(2, 6);
        }
    },

    PUNTEADO_COMPLEJO("Punteado Complejo"){
        @Override
        public void apply(DrawingContext dc){
            dc.setLineWidth(3);
            dc.setLineCap(LineCap.SQUARE);
            dc.setLineDashes(25, 10, 15, 10);
        }
    };

    private final String borderName;

    Border(String displayName){
        this.borderName = displayName;
    }

    /**
     * Aplica el estilo de borde al contexto de dibujo especificado.
     *
     * @param dc    El contexto de dibujo sobre el cual aplicar el borde.
     */
    public abstract void apply(DrawingContext dc);

    @Override
    public String toString(){
        return borderName;
    }
}
