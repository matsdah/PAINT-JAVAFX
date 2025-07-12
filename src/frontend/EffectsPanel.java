package src.frontend;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import src.backend.CanvasFigure;
import src.backend.model.EffectType;

import java.util.HashMap;
import java.util.Map;

public class EffectsPanel extends Bar{

    private final Label effectsLabel = createTitleLabel("Efectos:");
    private final CheckBox lightenCheckbox = new CheckBox("Aclaramiento");
    private final CheckBox darkenCheckbox = new CheckBox("Oscurecimiento");
    private final CheckBox mirrorHCheckbox = new CheckBox("Espejo Horizontal");
    private final CheckBox mirrorVCheckbox = new CheckBox("Espejo Vertical");

    private final HBox effectsRow = buildCheckboxRow(lightenCheckbox, darkenCheckbox, mirrorHCheckbox, mirrorVCheckbox);
    private final Map<CheckBox, EffectType> effectMap = new HashMap<>();

    public EffectsPanel(PaintPane paintPane){
        super(paintPane);
        initializeEffectMap();
        lightenCheckbox.setOnAction(event -> onEffectChanged());
        darkenCheckbox.setOnAction(event -> onEffectChanged());
        mirrorHCheckbox.setOnAction(event -> onEffectChanged());
        mirrorVCheckbox.setOnAction(event -> onEffectChanged());
        HBox buttonsTopBox = buildHBox(effectsLabel, effectsRow);
    }

    private void initializeEffectMap(){
        effectMap.put(lightenCheckbox, EffectType.LIGHTENED);
        effectMap.put(darkenCheckbox, EffectType.DARKENED);
        effectMap.put(mirrorHCheckbox, EffectType.HORIZONTAL_MIRROR);
        effectMap.put(mirrorVCheckbox, EffectType.VERTICAL_MIRROR);
    }

    private HBox buildHBox(Node... nodes){
        HBox topMenu = new HBox(10, effectsLabel, effectsRow);
        topMenu.setPadding(new Insets(5));
        topMenu.setStyle("-fx-background-color: #EEEEEE");
        return topMenu;
    }

    /**
     * Actualiza el estado de la barra de efectos segun la figura seleccionada.
     *
     * @param figure 	Figura cuyos efectos se reflejan en la barra.
     */
    public void updateEffectCheckboxes(CanvasFigure figure){
        for(Map.Entry<CheckBox, EffectType> entry : effectMap.entrySet()){
            entry.getKey().setSelected(figure.hasEffect(entry.getValue()));
        }
    }

    /**
     * Agrega o elimina efectos de la figura seleccionada según el estado de la barra.
     */
    public void onEffectChanged(CanvasFigure selectedFigure){
        if(selectedFigure != null){
            for(Map.Entry<CheckBox, EffectType> entry : effectMap.entrySet()){
                if(entry.getKey().isSelected()){
                    selectedFigure.addEffect(entry.getValue());
                }else{
                    selectedFigure.removeEffect(entry.getValue());
                }
            }
        }
    }

    public boolean isSelected(EffectType effect){
        boolean toReturn = false;
        for(Map.Entry<CheckBox, EffectType> entry : effectMap.entrySet()){
            if(entry.getValue().equals(effect)){
                toReturn = entry.getKey().isSelected();
            }
        }
        return toReturn;
    }

}
