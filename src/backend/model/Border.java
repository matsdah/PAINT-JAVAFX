package src.backend.model;
import src.backend.DrawingContext;

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
        public void apply(DrawingContext dc) {
            dc.setLineWidth(5);
            dc.setLineCap(LineCap.BUTT);
            dc.setLineDashes(1, 1);
        }
    },

    PUNTEADO_FINO("Punteado Fino"){
        @Override
        public void apply(DrawingContext dc) {
            dc.setLineWidth(1);
            dc.setLineCap(LineCap.ROUND);
            dc.setLineDashes(2, 6);
        }
    },

    PUNTEADO_COMPLEJO("Punteado Complejo"){
        @Override
        public void apply(DrawingContext dc) {
            dc.setLineWidth(3);
            dc.setLineCap(LineCap.SQUARE);
            dc.setLineDashes(25, 10, 15, 10);
        }
    };

    private final String borderName;

    Border(String displayName){
        this.borderName = displayName;
    }

    public abstract void apply(DrawingContext dc);

    @Override
    public String toString(){
        return borderName;
    }
}
