public class CanvasFigure{

    public class CanvasFigure(Figure fig, boolean isLightened, boolean isDarkened, boolean isVMirrored, boolean isHMirrored){
        this.fig = fig;
        this.isLightened = isLightened;
        this.isDarkened = isDarkened;
        this.isVMirrored = isVMirrored;
        this.isHMirrored = isHMirrored;
    }

    private Figure fig;
    private Figure lightenedFig;
    private Figure darkenedFig;
    private Figure vMirroredFig;
    private Figure hMirroredFig;
    private boolean isLightened;
    private boolean isDarkened;
    private boolean isVMirrored;
    private boolean isHMirrored;

    /*
     * Mueve la figura junto a todos los efectos. Cada figura debe saber moverse.
     */
    public void move(int x, int y){
        fig.move(x, y);
        lightenedFig.move(x, y);
        darkenedFig.move(x, y);
        vMirroredFig.move(x, y);
        hMirroredFig.move(x, y);
    }

    public void setOffLightened(){

    }

    public void setOnLightened(){
        if(!isLightened){

        }
    }
}