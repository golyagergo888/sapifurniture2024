package ro.sapientia.furniture.dto;

import ro.sapientia.furniture.model.FurnitureMaterial;
import ro.sapientia.furniture.model.FurnitureBunkBedType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class FurnitureBunkBedDTO implements Serializable {

    @NotNull
    @Min(1)
    private int width;

    @NotNull
    @Min(1)
    private int height;

    @NotNull
    @Min(1)
    private int depth;

    @NotNull
    private FurnitureMaterial material;

    @NotNull
    private FurnitureBunkBedType type;

    public FurnitureBunkBedDTO() { }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public FurnitureMaterial getMaterial() {
        return material;
    }

    public void setMaterial(FurnitureMaterial material) {
        this.material = material;
    }

    public FurnitureBunkBedType getType() {
        return type;
    }

    public void setType(FurnitureBunkBedType type) {
        this.type = type;
    }
}
