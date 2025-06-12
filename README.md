# Parte 1 - Cabeçalho e Pré-requisitos
# Xarlreiz-Attacker API

API REST para controle de ataques de pacotes UDP/TCP desenvolvida com Spring Boot.

## 🛠 Pré-requisitos

- Java JDK 24

# Parte 2 - Instruções de Execução

## 🚀 Como executar

### 1. Clone o repositório
```bash
git clone https://github.com/gdinizds/xarlreiz-attacker.git
cd xarlreiz-attacker
```

### 2. Construa o projeto
```bash
./gradlew build
```

### 3. Execute a aplicação
```bash
./gradlew bootRun
```

A API estará disponível em: `http://localhost:8080`
Interface Swagger UI: `http://localhost:8080/swagger-ui.html`

# Parte 3 - Endpoints e Exemplos

## 📡 Endpoints

### Iniciar Ataque
```
POST /attack/start
```

Body:
```json
{
  "target": "example.com",
  "port": 80,
  "protocol": "UDP"
}
```


### Parar Ataque
```
POST /attack/stop
```


### Status do Ataque
```
GET /attack/status
```


## 🔍 Exemplos de Uso

### Iniciar um ataque UDP
```shell script
curl -X POST http://localhost:8080/attack/start \
  -H "Content-Type: application/json" \
  -d '{
    "target": "example.com",
    "port": 80,
    "protocol": "UDP"
  }'
```

### Parar o ataque
```shell script
curl -X POST http://localhost:8080/attack/stop
```

### Verificar status
```shell script
curl http://localhost:8080/attack/status
```
# Parte 4 - Configuração

## ⚙️ Configuração

### application.yml
```yaml
server:
  port: 8080

springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
    operationsSorter: method
    tryItOutEnabled: true
```


## 🔐 Validações

- Target: Aceita IPs e URLs válidos
- Port: Valores entre 1 e 65535
- Protocol: UDP ou TCP

# Parte 5 - Estrutura do Projeto
## 🏗 Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── onzecincocincodoet/
│   │           └── xarlreizattacker/
│   │               ├── XarlreizAttackerApplication.java
│   │               ├── controller/
│   │               │   └── PacketFloodController.java
│   │               ├── service/
│   │               │   ├── AttackOrchestrator.java
│   │               │   ├── AttackService.java
│   │               │   └── impl/
│   │               │       ├── TcpAttackService.java
│   │               │       └── UdpAttackService.java
│   │               ├── dto/
│   │               │   ├── AttackRequest.java
│   │               │   └── AttackStatus.java
│   │               ├── exception/
│   │               │   ├── GlobalExceptionHandler.java
│   │               │   └── InvalidTargetException.java
│   │               └── util/
│   │                   └── NetworkUtils.java
│   └── resources/
│       └── application.yml
```
# Parte 6 - Notas Importantes e Troubleshooting
## 📝 Notas Importantes

1. **Configuração de Porta**: Por padrão, a aplicação roda na porta 8080. Para alterar:
```shell script
./gradlew bootRun --args='--server.port=9090'
```
2. **Logs**: Os logs são exibidos no console por padrão

## 🔧 Troubleshooting

### Porta em uso
Se a porta 8080 estiver em uso, você verá um erro como:
```
Web server failed to start. Port 8080 was already in use.
```

Solução: Use uma porta diferente como mostrado acima.

### Permissão negada
Se encontrar erro de permissão no Linux/Mac:
```shell script
chmod +x gradlew
```


### Java não encontrado
Se receber erro "Java not found":
1. Verifique se o JAVA_HOME está configurado:
```shell script
echo $JAVA_HOME
```

2. Configure se necessário:
```shell script
export JAVA_HOME=/path/to/your/java
export PATH=$JAVA_HOME/bin:$PATH
```


