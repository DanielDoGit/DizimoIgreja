package comum;

public enum NomeClasse {
	
	BALANCEDIZIMO("Balancete dizimo"),	MISSA("Missa");
	
	String b;
	
	private NomeClasse(String c) {
		this.b = c;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}
	
	

}
