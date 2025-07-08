package src.backend.model;

public class Circle extends Ellipse{

    /**
     * Crea un nuevo círculo a partir de un punto central y otro punto sobre el borde del círculo.
     *
     * @param centerPoint   El punto central del círculo.
     * @param radiusPoint   Un punto sobre la circunferencia del círculo, usado para calcular el radio.
     * @param fillColor     El color de relleno del círculo.
     * @param border        El estilo de borde del círculo.
     */
    public Circle(Point centerPoint, Point radiusPoint, Color fillColor, Border border){
        double auxX = centerPoint.getX() - radiusPoint.getX();
        double auxY = centerPoint.getY() - radiusPoint.getY();
        double radius = Math.sqrt((auxX * auxX) + (auxY * auxY));
        Point cornerPoint = new Point(centerPoint.getX() + radius, centerPoint.getY() + radius);
        super(centerPoint, cornerPoint, fillColor, border);
    }

    public double getRadius(){
        return difX;
    }

    @Override
    public String toString(){
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, getRadius());
    }
}