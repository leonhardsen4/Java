package procon.protocolo.modelo;

public class Contato {
	private int ID;
	private String telefone;
	private String email;

	// Contructor
	public Contato() {
	}

	// Getters and Setters
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
