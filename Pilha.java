class Pilha {
	private No topo;

	public Pilha() {
		topo = null;
	}

	//informa se a pilha está vazia
	public boolean pilhaVazia() {
		return topo == null; //coloca o elemento no topo da pilha
	}

	public void push(String c) {
		No no = new No();
		no.conteudo = c;
		no.proximo = topo;
		topo = no;
	}

	//retira o elemento do topo da pilha
	public String pop() {
		if (pilhaVazia()) {
			return "#";
		} else {
			String c = topo.conteudo;
			topo = topo.proximo;
			return c;
		}
	}

	//remove todos os elementos da pilha 
	public void limpar() {
		topo = null;
	}

	public void imprimir() {
		if (pilhaVazia()) {
			System.out.println("Pilha vazia");
		} else {
			No no = topo;
			while (no != null) {
				System.out.print(no.conteudo + " ");
				no = no.proximo;
			}
			System.out.println();
		}
	}
}
