import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		String[] separador; //Para quebrar a expressão
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Digite a expressão matemática: "); //exemplo: ((2+3)*5)
		String entrada = scan.next();
		
		String expressao = infixaToPosfixa(entrada);
		System.out.println("Notação pós-fixa: " + expressao);
		separador = expressao.split("");
		String conta = ""; //atribuindo a conta a notação pós fixa
		Pilha p = new Pilha();
		for (int i = 0; i < separador.length; i++) {
			p.push(separador[i]);
		}
		System.out.println(" ");
		for (int i = 0; i < separador.length; i++) {
			conta += p.pop();
		}
		separador = conta.split("");
		Arvore arvore = new Arvore();
		for (int i = 0; i < separador.length; i++) {
			arvore.add = false;
			arvore.raiz = arvore.criar(arvore.raiz, separador[i]);
		}
		
		System.out.printf("Esquerda Direita Raiz: ");
		arvore.imprimirEsqDirRaiz(arvore.raiz);
		System.out.println();
		arvore.soma(arvore.raiz);
		
		scan.close();

	}
	
	//Converter uma expressão aritmética infixa comum para uma expressão pós-fixa
	public static String infixaToPosfixa(String exp) {
		
		//Mantem os parenteses e operadores
		Pilha pilha = new Pilha();  
		String resultado = "", c;
		String[] v = exp.split("");
		
		for (int i = 0; i < v.length; i++) {
			switch (v[i]) {
			case "(": 
				// empilha o "(" "*" "/"
				pilha.push(v[i]);
				break;
			case ")": 
				//desempilha enquanto o topo da pilha for diferente de "(" ou "/"
				c = pilha.pop(); 
				while (!c.equals("(") && !c.equals("#")) {
					resultado = resultado + c;
					c = pilha.pop(); // retorna o caractere # quando a pilha estiver vazia
				}
				break;
			case "+":
			case "-": 
				//desempilha enquanto o topo da pilha for diferente de "(" "/"
				c = pilha.pop();
				while (!c.equals("(") && !c.equals("#")) {
					resultado = resultado + c;
					c = pilha.pop();
				}
				pilha.push(c);
				pilha.push(v[i]);
				break;
			case "*":
			case "/":
				//desempilha enquanto o topo da pilha for diferente de "(" "+" e "-/"
				c = pilha.pop();
				while (!c.equals("(") && !c.equals("+") && !c.equals("-") && !c.equals("#")) {
					resultado = resultado + c;
					c = pilha.pop();
				}
				pilha.push(c);
				pilha.push(v[i]);
				break;
			default: 
				//empilha o operando 
				resultado = resultado + v[i];
			}
		}
		return resultado;
	}

}
