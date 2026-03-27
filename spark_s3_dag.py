from airflow import DAG
from airflow.operators.bash import BashOperator
from datetime import datetime

with DAG(
    dag_id="spark_s3_pipeline",
    start_date=datetime(2024, 1, 1),
    schedule_interval=None,  # manual
    catchup=False,
    tags=["spark", "s3"]
) as dag:

    executar_spark = BashOperator(
        task_id="executar_spark_job",
        bash_command="""
            java --add-opens java.base/sun.nio.ch=ALL-UNNAMED \
                 --add-opens java.base/java.nio=ALL-UNNAMED \
                 --add-opens java.base/java.lang=ALL-UNNAMED \
                 --add-opens java.base/java.util=ALL-UNNAMED \
                 -jar /home/isara/programatalentos/target/programatalentos-1.0-SNAPSHOT.jar
        """
    )
