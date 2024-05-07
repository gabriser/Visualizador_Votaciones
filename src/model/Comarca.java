package model;

import java.util.Objects;

public class Comarca {

	private String comarca;

	public Comarca(String comarca) {
		this.comarca = comarca;
	}

	public String getComarca() {
		return comarca;
	}

	public void setComarca(String comarca) {
		this.comarca = comarca;
	}

	@Override
	public String toString() {
		return "Comarca [comarca=" + comarca + "]";
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comarca comarca1 = (Comarca) o;
        return Objects.equals(comarca, comarca1.comarca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comarca);
    }
	
}
