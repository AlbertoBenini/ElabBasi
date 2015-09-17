package it.ablp;
import java.io.Serializable;

public class UtilCrono implements Serializable {
	private String datain;
	private String dataf;
	private String motivo;
	private String resp;
	//private String cods;
	private String nomed;
	
	public UtilCrono(){
		datain=null;
		dataf=null;
		motivo=null;
		resp=null;
	//cods=null;
		nomed=null;
		}
	
	public void setDatain(String datain){
		this.datain=datain;
	}
	public String getDatain(){
		return this.datain;
	}
	
	public void setDataf(String dataf){
		this.dataf=dataf;
	}
	public String getDataf(){
		return this.dataf;
	}
	
	public void setMotivo(String motivo){
		this.motivo=motivo;
	}
	public String getMotivo(){
		return this.motivo;
	}
	
	public void setResp(String resp){
		this.resp=resp;
	}
	public String getResp(){
		return this.resp;
	}
	/*
	public void setCods(String cods){
		this.cods=cods;
	}
	public String getCods(){
		return this.cods;
	}
	*/
	public void setNomed(String nomed){
		this.nomed=nomed;
	}
	public String getNomed(){
		return this.nomed;
	}
	
}
