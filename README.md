# 🚀 Pipeline de Dados com Airflow, Spark e AWS S3

## 📌 Descrição

Este projeto demonstra a construção de um pipeline de dados utilizando **Apache Airflow** para orquestração, **Apache Spark** para processamento e **Amazon S3** como camada de armazenamento.

O objetivo é simular um fluxo de dados onde:

* Um arquivo é ingerido a partir de um bucket no S3
* O Spark realiza o processamento desses dados
* O resultado é salvo em outro bucket no S3
* Todo o processo é orquestrado via Airflow rodando localmente

---

## 🏗️ Arquitetura

1. Upload do arquivo de entrada no S3
2. Airflow inicia o pipeline (DAG)
3. Spark processa os dados
4. Resultado é salvo no S3 (bucket de saída)

---

## 📂 Estrutura do Projeto

```
spark-s3-airflow-pipeline/
│
├── src/main/java/        # Código Java (Spark)
├── spark_s3_dag.py       # DAG do Airflow
├── pom.xml               # Configuração Maven
├── .gitignore
└── README.md
```

---

## ⚙️ Tecnologias Utilizadas

* Apache Airflow
* Apache Spark
* AWS S3
* Java (Spark Job)
* Python (DAG Airflow)
* Maven

---

## ☁️ Pré-requisitos

Antes de rodar o projeto, você precisa ter instalado:

* Java 11+
* Maven
* Python 3+
* Apache Airflow
* Apache Spark
* Conta na AWS

---

## 🔐 Configuração da AWS

1. Criar um bucket no S3:

   * Exemplo:

     * `input-bucket-dados`
     * `output-bucket-dados`

2. Configurar credenciais AWS:

Via terminal:

```
aws configure
```

Ou via variáveis de ambiente:

```
AWS_ACCESS_KEY_ID=...
AWS_SECRET_ACCESS_KEY=...
```

---

## ▶️ Como Executar o Projeto

### 1. Subir o Airflow

Inicializar o banco:

```
airflow db init
```

Criar usuário:

```
airflow users create \
--username admin \
--password admin \
--firstname Admin \
--lastname User \
--role Admin \
--email admin@email.com
```

Iniciar serviços:

```
airflow webserver --port 8080
airflow scheduler
```

---

### 2. Configurar o DAG

Coloque o arquivo:

```
spark_s3_dag.py
```

Na pasta:

```
~/airflow/dags/
```

---

### 3. Build do projeto Spark

```
mvn clean package
```

Isso vai gerar um `.jar` dentro de:

```
target/
```

---

### 4. Upload do arquivo de entrada

Envie um arquivo para o bucket de entrada:

Exemplo:

```
s3://input-bucket-dados/entrada.csv
```

---

### 5. Executar o Pipeline

1. Acesse:

```
http://localhost:8080
```

2. Ative o DAG

3. Execute manualmente ou aguarde o agendamento

---

## 🔄 Fluxo do Pipeline

* Airflow dispara o job
* Spark lê arquivo do S3
* Processa os dados
* Salva resultado no bucket de saída

Exemplo de saída:

```
s3://output-bucket-dados/resultado.csv
```

---


