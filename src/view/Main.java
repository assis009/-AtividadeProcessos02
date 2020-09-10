package view;

import javax.swing.JOptionPane;

import controller.Metodos;

public class Main {

	public static void main(String[] args) {
		
		String so = System.getProperty("os.name");
		Metodos m = new Metodos();
		
		System.out.println(so);
		String nome;
		int PID;
		int Opc = 0;
        while (Opc != 10){
            Opc = Integer.parseInt(JOptionPane.showInputDialog("\n      Atividade 2 \n 1 – Ver processos \n 2 – Matar processo usando PID \n 3 - Matar processo usando NOME \n 10 - Finalizar "));
            switch (Opc){
                case 1:
                	m.processosAtivos(so);//função que mostra os processos ativos 
                	System.out.println(" ");
                    break;
                case 2:
                	PID = Integer.parseInt(JOptionPane.showInputDialog("Digite o PID do Processo que deseja matar: "));
                	m.killProcessPID(so, PID);//mata os processos pelo PID
                	System.out.println(" ");
                    break;
                case 3:
                	nome = JOptionPane.showInputDialog("Digite o nome do Processo que deseja matar: ");
                	m.killProcessNome(so, nome);// mata os processos pelo NOME
                	System.out.println(" ");
                    break;
                case 10:
                    JOptionPane.showMessageDialog(null, "Fim!");
                    break;
                default:
                    System.err.println("Ivalido, digite novamente");
            }
        }
	}

}
