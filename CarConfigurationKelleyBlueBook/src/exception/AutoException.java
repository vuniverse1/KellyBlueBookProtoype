package exception;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import exception.Fix1to100;
import Adapter.FixAuto;

public class AutoException extends Exception implements FixAuto{
	private static final long serialVersionUID = 1L;
	private int errorno;
	private String errormsg;
	
	 public enum ErrorType {
	        MISSING_PRICE(1),
	        MISSING_OPTION_SET(2),
	        MISSING_OPTION(3),
	        MISSING_FILENAME(4);
		

	        private final int errorCode;

	        ErrorType(int errorCode) {
	            this.errorCode = errorCode;
	        }
	        public int getErrorCode() {
	            return errorCode;
	        }
	    }

	public AutoException(int errorno) {
		this.errorno = errorno;
		this.printmyproblem();
	}

	public AutoException(String errormsg) {
		this.errormsg = errormsg;
		this.printmyproblem();
	}

	public AutoException(int errorno, String errormsg) {
		this.errorno = errorno;
		this.errormsg = errormsg;
		this.printmyproblem();
		this.writetologfile();
	}
	public AutoException(ErrorType missingPrice) {
		// TODO Auto-generated constructor stub
		this.errorno = missingPrice.getErrorCode();
        this.printmyproblem();
	}
	public AutoException(ErrorType errorType, String errormsg) {
        this.errorno = errorType.getErrorCode();
        this.errormsg = errormsg;
        this.printmyproblem();
        this.writetologfile();
    }

	public void printmyproblem() {
		System.out.printf("\nError #%d\tError message:%s\n", this.errorno, this.errormsg);
	}
	private void writetologfile() {
		try {
			DateFormat d1 = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
			Date date = new Date();
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("errlog.txt", true)));
			writer.println("[" + d1.format(date) + "] " + this.errormsg);
			writer.close();
		} catch (IOException e) {
			System.out.println("IO Error, try restarting the process");
			System.exit(1);
		} finally {
		}
	}
	public int getErrorno() {
		return errorno;
	}

	public void setErrorno(int errorno) {
		this.errorno = errorno;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public void fix(int errorno) {
		Fix1to100 f1 = new Fix1to100();

		switch (errorno) {
		case 1:
			 f1.fix1(errorno);
			 break;
		case 2:
			f1.fix2(errorno);
			break;
		case 3:
			f1.fix3(errorno);
			break;
		case 4:
			f1.fix4(errorno);
			break;
		case 5:
			f1.fix5(errorno);
			break;
		
		}
		
	}
}
