
public class Arvore {
	public No raiz;
	static float soma = 0;
	public boolean add;
	
	// retorna a altura de uma arvore binaria 
	public int altura(No no) {
		if (no == null) {
			return -1; // arvore vazia 
		} else {
			int altura_esqeda = altura(no.esquerdo);
			int altura_direita = altura(no.direito);
			return altura_esqeda < altura_direita ? altura_direita + 1 : altura_esqeda + 1;
		}
	}

	// libera a arvore que se encontra abaixo do no 
	public No limpar(No no) {
		if (no != null) {
			no.esquerdo = limpar(no.esquerdo); // limpa o lado esquerdo 
			no.direito = limpar(no.direito); // limpa o lado direito 
		}
		return null;
	}
	
	//Descobre se existe NOs
	public boolean buscar(No no, String nro) {
		if (no == null) {
			return false; // nao encontrou o no 
		} else {
			return nro == no.conteudo || buscar(no.esquerdo, nro) || buscar(no.direito, nro);
		}
	}
	//Descobre os operadores da expressao
	boolean Operador(String s) {
		try {
			Integer.parseInt(s);
			return false;

		} catch (Exception e) {
			return true;
		}
	}

	// Verifica se conteudo é numero ou nao
	boolean nao_e_numero(No no) {
		try {// se for numero retorna false
			Integer.parseInt(no.conteudo);
			return false;
		} catch (Exception e) {// se nao for numero retorna true
			return true;
		}
	}

	public No criar(No no, String str) {
		if (no == null) { // Arvore vazia
			no = new No();
			no.conteudo = str;
			add = true; // condiÃ§Ã£o para nao entrar no ultimo if
		} else if (nao_e_numero(no.direito)) {
			no.direito = criar(no.direito, str);
		}
		// Caso nao encontrou nenhum lugar do lado direito, insere no lado esquerdo
		 
		if ((!add) & nao_e_numero(no.esquerdo)) {
			no.esquerdo = criar(no.esquerdo, str);
		}

		return no;
	}

	// varredura posfixa ou pos-ordem 
	public void imprimirEsqDirRaiz(No no) {
		if (no != null) {
			imprimirEsqDirRaiz(no.esquerdo);
			imprimirEsqDirRaiz(no.direito);
			System.out.print(no.conteudo + " ");
		}
	}

	public void soma(No no) {
		if (no != null) {
			// Percorre a arvore (Esquerda Direita Raiz) ate encontrar um numero Quando encontrar, pega o primeiro operando e faz os calculos

			soma(no.esquerdo);
			soma(no.direito);
			if (Operador(no.conteudo)) {
				switch (no.conteudo) {
				// Quando for calcular, o nao do operando recebe o valor do  calculo e anula os nao direito e esquerdo
			
				case "+":
					soma = Float.parseFloat(no.esquerdo.conteudo) + Float.parseFloat(no.direito.conteudo);
					System.out.println("Soma: " + soma);
					no.conteudo = "" + soma;
					no.direito = null;
					no.esquerdo = null;
					break;
				case "-":
					soma = Float.parseFloat(no.esquerdo.conteudo) - Float.parseFloat(no.direito.conteudo);
					System.out.println("Subtração: " + soma);
					no.conteudo = "" + soma;
					no.direito = null;
					no.esquerdo = null;
					break;
				case "/":
					soma = Float.parseFloat(no.esquerdo.conteudo) / Float.parseFloat(no.direito.conteudo);
					System.out.println("Divisão: " + soma);
					no.conteudo = "" + soma;
					no.direito = null;
					no.esquerdo = null;
					break;
				case "*":
					soma = Float.parseFloat(no.esquerdo.conteudo) * Float.parseFloat(no.direito.conteudo);
					System.out.println("Multiplicação: " + soma);
					no.conteudo = "" + soma;
					no.direito = null;
					no.esquerdo = null;
					break;
				}
			}
		}
	}
}
