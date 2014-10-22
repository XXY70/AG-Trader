package items;

import java.util.Date;

import main.AGActions;

public class Order {
	private Date executionDate;
	private String name;
	private int wkn;
	private double limit;
	private Date expirationDate;

	public Order(int wkn, double limit, Date exDate) {
		this.wkn = wkn;
		this.executionDate = exDate;
		this.name = AGActions.getAGName(wkn);
		this.limit = limit;
	}

	public Order(String name, double limit, Date exDate) {
		this.wkn = AGActions.getWKN(name);
		this.executionDate = exDate;
		this.name = name;
		this.limit = limit;
	}

	public double getLimit() {
		return limit;
	}

	public void setLimit(double limit) {
		this.limit = limit;
	}

	public Date getExecutionDate() {
		return executionDate;
	}

	public String getName() {
		return name;
	}

	public int getWkn() {
		return wkn;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}
}
