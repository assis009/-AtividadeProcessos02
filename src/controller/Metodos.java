package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Metodos {

	public Metodos(){
		super();
	}
	//mostra os processos ativos windows 
	public void processosAtivos(String sistemaOperacional) {
		
		if(sistemaOperacional.contains("Linux")){//verifica o sistema operacional 
			LinuxProcessos();
			
		}else{
			
			try {
				
				Process p = Runtime.getRuntime().exec("TASKLIST /FO TABLE");
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				
				while (linha != null) {
					System.out.println(linha);
					linha = buffer.readLine();
				}
				
				buffer.close();
				leitor.close();
				fluxo.close();
				
			} catch (Exception e) {
				
				String msgError = e.getMessage();
				System.err.println(msgError);
			}
		}
	}
	
	//Mata processos por PID windows 
	public void killProcessPID(String sistemaOperacional, int PID) {
		
		if(sistemaOperacional.contains("Linux")){//verifica o sistema operacional 
			LinuxPID(PID);
			
		}else{
			
			String cmdPid = "TASKKILL /PID";
			int pid = 0;
			StringBuffer buffer = new StringBuffer();
			
			try {
				
				pid = PID;
				buffer.append(cmdPid);
				buffer.append(" ");
				buffer.append(pid);
				
			} catch (Exception e) {
				System.err.println(e);
			}
			
			try {
				
				Runtime.getRuntime().exec(buffer.toString());
			} catch (Exception e) {
				
				String msgError = e.getMessage();
				if (msgError.contains("740")) {
					StringBuffer buffer2 = new StringBuffer();
					buffer2.append("cmd /c");
					buffer2.append(" ");
					buffer2.append(buffer);
					
					try {
						Runtime.getRuntime().exec(buffer2.toString());
					} catch (Exception e1) {
						String msgError1 = e1.getMessage();
						System.err.println(msgError1);
					}
					
				} else {
					System.err.println(msgError);
				}
			}
		}
	}
	
	//mata processo pelo nome windows 
	public void killProcessNome(String sistemaOperacional, String nome) {
		
		if(sistemaOperacional.contains("Linux")){//verifica o sistema operacional 
			LinuxNome(nome);
			
		}else{
			String cmdNome = "TASKKILL /IM";
			String Nome = ("");
			StringBuffer buffer = new StringBuffer();
			try {
				Nome = nome;
				buffer.append(cmdNome);
				buffer.append(" ");
				buffer.append(Nome);
			} catch (Exception e) {
				System.err.println(e);
			}
			
			try {
				Runtime.getRuntime().exec(buffer.toString());
			} catch (Exception e) {
				String msgError = e.getMessage();
				if (msgError.contains("740")) {
					StringBuffer buffer2 = new StringBuffer();
					buffer2.append("cmd /c");
					buffer2.append(" ");
					buffer2.append(buffer);
					try {
						Runtime.getRuntime().exec(buffer2.toString());
					} catch (Exception e1) {
						String msgError1 = e1.getMessage();
						System.err.println(msgError1);
					}
				} else {
					System.err.println(msgError);
				}
			}
		}
	}
	
	//processos ativos no linux 
	public void LinuxProcessos(){
		
		try {
			
			Process p = Runtime.getRuntime().exec("ps -ef ");
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			
			while (linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
			
			buffer.close();
			leitor.close();
			fluxo.close();
			
		} catch (Exception e) {
			
			String msgError = e.getMessage();
			System.err.println(msgError);
		}
	}
	
	public void LinuxPID(int PID){
		
		String shellPid = "kill -9 ";
		int pid = 0;
		StringBuffer buffer = new StringBuffer();
		
		try {
			
			pid = PID;
			buffer.append(shellPid);
			buffer.append(" ");
			buffer.append(pid);
			
		} catch (Exception e) {
			System.err.println(e);
		}
		
		try {
			
			Runtime.getRuntime().exec(buffer.toString());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void LinuxNome(String nome){
		
		String shellNome = "kill -9 ";
		String Nome = ("");
		StringBuffer buffer = new StringBuffer();
		try {
			Nome = nome;
			buffer.append(shellNome);
			buffer.append(" ");
			buffer.append(Nome);
		} catch (Exception e) {
			System.err.println(e);
		}
		
		try {
			Runtime.getRuntime().exec(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
