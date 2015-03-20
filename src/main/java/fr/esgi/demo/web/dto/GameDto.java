package fr.esgi.demo.web.dto;

/**
 *
 * DTO (Data Tansfer Object) : objet qui va vivre à l'extérieur
 */
public class GameDto {
    private Long id;
    private String name;
    private Type type;

    public enum Type {
        RPG, FPS;
    }

    public GameDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
