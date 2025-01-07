package ro.sapientia.furniture.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "furniture_bunk_bed")
public class FurnitureBunkBed implements Serializable{
    private static final long serialVersionUID = 1L;
    public static Object FurnitureMaterial;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "furniture_bunk_bed_seq")
    @SequenceGenerator(
            name = "furniture_bunk_bed_seq",
            sequenceName = "furniture_bunk_bed_seq",
            allocationSize = 1
    )
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;

    @Column(name = "depth")
    private int depth;

    @Column(name = "material", nullable = false)
    @Enumerated(EnumType.STRING)
    private FurnitureMaterial material;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private FurnitureBunkBedType type;

    public FurnitureBunkBed() {
    }

    public FurnitureBunkBed(int width, int height, int depth, FurnitureMaterial material, FurnitureBunkBedType type) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.material = material;
        this.type = type;
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
        return "FurnitureBunkBed{" +
                "id=" + id +
                ", width=" + width +
                ", height=" + height +
                ", depth=" + depth +
                ", material=" + material +
                ", type=" + type +
                '}';
    }
}
