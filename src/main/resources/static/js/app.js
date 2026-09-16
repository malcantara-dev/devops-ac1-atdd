const { createApp } = Vue;

createApp({
    data() {
        return {
            mensagem: '',
            usuarios: [],
            cursos: [],
            matriculas: [],
            usuario: { nome: '' },
            curso: { titulo: '' },
            matricula: { usuarioId: '', cursoId: '', bonus: 'false' },
            nota: { matriculaId: '', valor: '' },
            consultaUsuarioId: ''
        };
    },
    methods: {
        async request(path, options = {}) {
            const response = await fetch(path, {
                ...options,
                headers: { 'Content-Type': 'application/json' }
            });
            const texto = await response.text();
            if (!response.ok) throw new Error(texto || 'Erro na requisicao');
            return texto ? JSON.parse(texto) : null;
        },
        async executar(acao, mensagemOk) {
            try {
                await acao();
                this.mensagem = mensagemOk;
            } catch (erro) {
                this.mensagem = erro.message;
            }
        },
        async listarUsuarios() {
            this.usuarios = await this.request('/api/usuarios');
        },
        async listarCursos() {
            this.cursos = await this.request('/api/cursos');
        },
        criarUsuario() {
            this.executar(async () => {
                await this.request('/api/usuarios', { method: 'POST', body: JSON.stringify(this.usuario) });
                this.usuario = { nome: '' };
                await this.listarUsuarios();
            }, 'Usuario criado');
        },
        criarCurso() {
            this.executar(async () => {
                await this.request('/api/cursos', { method: 'POST', body: JSON.stringify(this.curso) });
                this.curso = { titulo: '' };
                await this.listarCursos();
            }, 'Curso criado');
        },
        matricular() {
            const bonus = this.matricula.bonus === 'true';
            const path = bonus ? '/api/matriculas/bonus' : '/api/matriculas';
            const payload = {
                usuarioId: Number(this.matricula.usuarioId),
                cursoId: Number(this.matricula.cursoId)
            };
            this.executar(async () => {
                const criada = await this.request(path, { method: 'POST', body: JSON.stringify(payload) });
                this.consultaUsuarioId = String(criada.usuarioId);
                await this.listarUsuarios();
                await this.listarMatriculas();
            }, bonus ? 'Curso bonus desbloqueado' : 'Matricula criada');
        },
        enviarNota(caminho, mensagemOk) {
            const payload = { nota: Number(this.nota.valor) };
            this.executar(async () => {
                await this.request(`/api/matriculas/${this.nota.matriculaId}/${caminho}`,
                    { method: 'PUT', body: JSON.stringify(payload) });
                await this.listarUsuarios();
                await this.listarMatriculas();
            }, mensagemOk);
        },
        async listarMatriculas() {
            if (!this.consultaUsuarioId) return;
            this.matriculas = await this.request(`/api/matriculas/usuario/${this.consultaUsuarioId}`);
        }
    },
    mounted() {
        this.listarUsuarios();
        this.listarCursos();
    }
}).mount('#app');
