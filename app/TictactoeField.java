package app;

public class TictactoeField {
	
	private char status;
	
	public TictactoeField() {
	}
	
	public TictactoeField(char status) {
		this.status = status;
	}
	
    public void setStatusX() {
    	this.status = 'X';
    }

    public void setStatusO() {
    	this.status = 'O';
    }
    
    public char getStatus() {
    	return status;
    }

    public boolean isStatusNull() {
    	return status == 0;
    }
    
	public String toString() {
		return "" + this.status;
	}	
}
