package model;

import java.util.Objects;

public class Municipi {

	private String municipi;

	public Municipi(String municipi) {
		this.municipi = municipi;
	}

	public String getMunicipi() {
		return municipi;
	}

	public void setMunicipi(String municipi) {
		this.municipi = municipi;
	}

	@Override
	public String toString() {
		return "Municipi [municipi=" + municipi + "]";
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Municipi municipi1 = (Municipi) o;
        return Objects.equals(municipi, municipi1.municipi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(municipi);
    }
	
}
