package companyx.inventory.model;

import javax.persistence.*;

@Entity
public class Stock {

	private long id;
	private String item;
	private String description;
	private String loanedTo;

	public Stock() {

	}

	public Stock(String item, String description, String loanedTo) {
		this.item = item;
		this.description = description;
		this.loanedTo = loanedTo;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "first_name", nullable = false)
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	
	@Column(name = "last_name", nullable = false)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "email_address", nullable = false)
	public String getLoanedTo() {
		return loanedTo;
	}
	public void setLoanedTo(String loanedTo) {
		this.loanedTo = loanedTo;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", item=" + item + ", description=" + description + ", loanedTo=" + loanedTo
				+ "]";
	}
	
}
