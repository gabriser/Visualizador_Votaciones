package model;

import java.util.Objects;

public class Partit {

	private String sigles;
	private String partit;
	
	public Partit(String sigles, String partit) {
		this.sigles = sigles;
		this.partit = partit;
	}

	public String getSigles() {
		return sigles;
	}

	public void setSigles(String sigles) {
		this.sigles = sigles;
	}

	public String getPartit() {
		return partit;
	}

	public void setPartit(String partit) {
		this.partit = partit;
	}

	@Override
	public String toString() {
		return "Partit [sigles=" + sigles + ", partit=" + partit + "]";
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partit partit1 = (Partit) o;
        return Objects.equals(sigles, partit1.sigles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sigles);
    }
	
}
