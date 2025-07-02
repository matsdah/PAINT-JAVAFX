package src.backend.effects;

import src.backend.model.*;

public abstract class EffectFigure {
    private Figure fig;

    public void move(int x, int y){
        fig.move(x, y);
    }

}
