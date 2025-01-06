package ro.sapientia.furniture.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "furniture_bunk_bed")
public class FurnitureBunkBed implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_furniture_bunk_bed")
    @SequenceGenerator(name = "pk_furniture_bunk_bed", sequenceName = "pk_furniture_bunk_bed")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;

    @Column(name = "depth")
    private int depth;

    @Column(name = "material")
    @Enumerated(EnumType.STRING)
    private FurnitureMaterial material;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private FurnitureBunkBedType type;

    public FurnitureBunkBed() {
    }

    public Long getId() {
        return this.id;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getDepth() {
        return this.depth;
    }

    public FurnitureMaterial getMaterial() {
        return this.material;
    }

    public FurnitureBunkBedType getType() {
        return this.type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setMaterial(FurnitureMaterial material) {
        this.material = material;
    }

    public void setType(FurnitureBunkBedType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "FurnitureBunkBed(id=" + this.getId() + ", width=" + this.getWidth() + ", height=" + this.getHeight() +
                ", depth=" + this.getDepth() + ", material=" + this.getMaterial() + ", type=" + this.getType() + ")";
    }
}
