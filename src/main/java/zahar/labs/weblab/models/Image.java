package zahar.labs.weblab.models;

import javax.persistence.*;

@Entity
@Table(name = "product_image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "image_url")
    private String imageUrl;

    public Image() {
    }

    public Image(Product product, String imageUrl) {
        this.product = product;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", product=" + product +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
