const input = document.getElementById('cep-input');
const loading = document.getElementById('loading');
const erro = document.getElementById('erro');
const erroMsg = document.getElementById('erro-msg');
const resultado = document.getElementById('resultado');

input.addEventListener('input', aplicarMascara);
input.addEventListener('keydown', (e) => {
  if (e.key === 'Enter') buscar();
});

function aplicarMascara(e) {
  let valor = e.target.value.replace(/\D/g, '').slice(0, 8);
  if (valor.length > 5) {
    valor = valor.slice(0, 5) + '-' + valor.slice(5);
  }
  e.target.value = valor;
}

function setField(id, valor, fallback = '-') {
  const el = document.getElementById(id);
  el.textContent = (valor && valor.trim()) ? valor : fallback;
}

function mostrarErro(msg) {
  erroMsg.textContent = msg;
  erro.classList.remove('hidden');
  resultado.classList.add('hidden');
}

function ocultarTudo() {
  erro.classList.add('hidden');
  resultado.classList.add('hidden');
  loading.classList.add('hidden');
}

async function buscar() {
  const cep = input.value.replace(/\D/g, '');

  ocultarTudo();

  if (cep.length !== 8) {
    mostrarErro('CEP deve conter 8 dígitos.');
    return;
  }

  loading.classList.remove('hidden');

  try {
    const response = await fetch(`http://localhost:8080/api/cep/${cep}`);

    if (!response.ok) {
      throw new Error('Serviço indisponível.');
    }

    const data = await response.json();
    loading.classList.add('hidden');

    if (data.erro) {
      mostrarErro('CEP não encontrado. Verifique e tente novamente.');
      return;
    }

    setField('res-cep', data.cep);
    setField('res-logradouro', data.logradouro);
    setField('res-complemento', data.complemento);
    setField('res-bairro', data.bairro);
    setField('res-cidade', data.localidade);
    setField('res-uf', data.uf);
    setField('res-ddd', data.ddd ? `(${data.ddd})` : null);

    resultado.classList.remove('hidden');

  } catch (err) {
    loading.classList.add('hidden');
    mostrarErro('Erro ao conectar com o serviço. Tente novamente.');
  }
}
