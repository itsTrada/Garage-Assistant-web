package model;

public class Motorbike {
    private String id;
    private String producer;
    private String name;
    private String type;
    private String color;
    private String status;
    
	public Motorbike(String id, String producer, String name, String type,
			String color, String status) {
		this.id = id;
		this.producer = producer;
		this.name = name;
		this.type = type;
		this.color = color;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
