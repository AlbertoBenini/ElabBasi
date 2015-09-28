#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"mia_lib.h"

char inizio[] = "INSERT INTO ";
char valori[] = " VALUES (";
char fine[] = " );\n";

void insStrum();
void insPers();
void insDiv();
void insInc();
void insMan();
void insUtil();
void copia (char *dest, char *sorg);
int size(char * parola);


int main() {
	int scelta=1;
	int scelta_dopo=1;

	while(scelta!=0) {
		if(scelta_dopo != 0) {
			printf("1 se si vuole inserire tupla per Strumento\n");
			printf("2 se si vuole inserire tupla per Persona\n");
			printf("3 se si vuole inserire tupla per Divisione Ospedaliera\n");
			printf("4 se si vuole inserire tupla per In Carico\n");
			printf("5 se si vuole inserire tupla per Manutenzione\n");
			printf("6 se si vuole inserire tupla per Utilizzo Strumento\n");
			printf("0 per uscire\n");
			scanf("%d",&scelta);
		}
		
		
		if(scelta==1) insStrum();
		if(scelta==2) insPers();
		if(scelta==3) insDiv();
		if(scelta==4) insInc();
		if(scelta==5) insMan();
		if(scelta==6) insUtil();
		
		if(scelta!=0) {
		printf("se si vuole inserire un altro elemento uguale inserire 0, altrimenti 1:\n");
		scanf("%d",&scelta_dopo);
		}
	}
	return 0;
}

char * stringaIntero(int tipo, char *parola) {
	/*
		Tipo 0 = intero,
		Tipo 1 = stringa,
		Tipo 2 = intero finale,
		Tipo 3 = stringa finale;
	*/
	
	char * finale;
	int l = size(parola);
	
	if(tipo == 0) {
		l = l+3;
		finale = malloc(sizeof(char)*l);
		copia(finale,parola);
		strcat(finale," ,");
		
	}
	if(tipo == 1) {
		l=l+5;
		finale=malloc(sizeof(char)*l);
		strcat(finale," '");
		strcat(finale,parola);
		strcat(finale,"' ,");
	}
	if(tipo == 2) {
		finale = parola;
	}
	
	if(tipo == 3) {
		l=l+3;
		finale=malloc(sizeof(char)*l);
		strcat(finale,"'");
		strcat(finale,parola);
		strcat(finale,"'");
	}

	return finale;
}

int size(char * parola) {
	
	int c=0;
	while(parola[c]!='\0') {
		c++;
	}
	
	return c+1;
}

void copia (char *dest, char *sorg) {
	int i=0;
	while(sorg[i]!='\0') {
		*dest = sorg[i];
		dest++;
		i++;
	}
	*dest='\0';
} 

void insStrum() {
	FILE *fp;
	char parola[200];
	fp = fopen("InserimentoDati.sql","a");
	fprintf(fp, "%s", inizio);
	fprintf(fp, "%s", "strumento");
	fprintf(fp, "%s", valori);
	printf("Inserisci codice strumento: ");
	scanf("%s",parola);
	fprintf(fp, "%s", stringaIntero(1,parola));
	printf("inserisci nome strumento: ");
	scanf("%s",parola);
	fprintf(fp, "%s", stringaIntero(1,parola));
	printf("inserire watt strumento: ");
	scanf("%s",parola);
	fprintf(fp, "%s", stringaIntero(0,parola));
    printf("inserire ditta strumento: ");
	scanf("%s",parola);
	fprintf(fp, "%s", stringaIntero(1,parola));
	printf("inserisci modello strumento: ");
	scanf("%s",parola);
	fprintf(fp, "%s", stringaIntero(1,parola));
	printf("inserisci costo strumento: ");
	scanf("%s",parola);
	fprintf(fp, "%s", stringaIntero(0,parola));
	printf("inserisci anno strumento: ");
	scanf("%s",parola);
	fprintf(fp, "%s", stringaIntero(2,parola));
	fprintf(fp, "%s", fine);
	
	fclose(fp);
}

void insPers() {
    FILE *fp;
	char parola[200];
	fp = fopen("InserimentoDati.sql","a");
	fprintf(fp, "%s", inizio);
	fprintf(fp, "%s", "persona");
	fprintf(fp, "%s", valori);
    printf("inserisci id persona:\n");
    scanf("%s",parola);
    fprintf(fp,"%s", stringaIntero(1,parola));
    printf("inserisci nome persona:\n");
    scanf("%s",parola);
    fprintf(fp,"%s", stringaIntero(1,parola));
    printf("inserisci cognome persona:\n");
    scanf("%s",parola);
    fprintf(fp,"%s", stringaIntero(1,parola));
    printf("inserisci data assunzione persona (formato AAAA-MM-GG):\n");
    scanf("%s",parola);
    fprintf(fp,"%s", stringaIntero(1,parola));
    printf("inserisci password della persona:\n");
    scanf("%s",parola);
    fprintf(fp,"%s", stringaIntero(3,parola));
    fprintf(fp, "%s", fine);
	
	fclose(fp);
}

void insDiv() {
    FILE *fp;
	char parola[200];
    char *prova_desc;
	fp = fopen("InserimentoDati.sql","a");
	fprintf(fp, "%s", inizio);
	fprintf(fp, "%s", "divosp");
	fprintf(fp, "%s", valori);
    printf("inserisci nome divisione:\n");
    scanf("%s",parola);
    fprintf(fp,"%s", stringaIntero(1,parola));
    printf("inserisci rappresentatnte divisione:\n");
    scanf("%s",parola);
    fprintf(fp,"%s", stringaIntero(1,parola));
    printf("inserisci numero dipendenti divisione\n");
    scanf("%s",parola);
    fprintf(fp,"%s", stringaIntero(0,parola));
    printf("inserisci numero posti letto divisione\n");
    scanf("%s",parola);
    fprintf(fp,"%s", stringaIntero(0,parola));
    printf("inserisci descrizione divisione\n");
    prova_desc = leggiConSpazi();
    fprintf(fp,"%s", stringaIntero(3,parola));
    fprintf(fp, "%s", fine);
	
	fclose(fp);
    
}

void insInc() {
    FILE *fp;
	char parola[200];
    char *prova_desc;
	fp = fopen("InserimentoDati.sql","a");
	fprintf(fp, "%s", inizio);
	fprintf(fp, "%s", "incarico");
	fprintf(fp, "%s", valori);
    printf("inserisci codice strumento:\n");
    scanf("%s",parola);
	fprintf(fp, "%s", stringaIntero(1,parola));
    printf("inserisci nome divisione:1\n");
    scanf("%s",parola);
    fprintf(fp, "%s", stringaIntero(3,parola));
    fprintf(fp, "%s", fine);

	fclose(fp);
}

void insMan() {
    FILE *fp;
	char parola[200];
    char *prova_desc;
	fp = fopen("InserimentoDati.sql","a");
	fprintf(fp, "%s", inizio);
	fprintf(fp, "%s", "manutenzione");
	fprintf(fp, "%s", valori);
    printf("inserire codice strumento:\n");
    scanf("%s",parola);
	fprintf(fp, "%s", stringaIntero(1,parola));
    printf("inserire data manutenzione (formato AAAA-MM-GG):\n");
    scanf("%s",parola);
	fprintf(fp, "%s", stringaIntero(1,parola));
    printf("inserire durata manutenzione:\n");
    scanf("%s",parola);
	fprintf(fp, "%s", stringaIntero(1,parola));
    printf("inserire numero operatori:\n");
    scanf("%s",parola);
	fprintf(fp, "%s", stringaIntero(0,parola));
    printf("inserire ditta manutenzione:\n");
    scanf("%s",parola);
	fprintf(fp, "%s", stringaIntero(1,parola));
    printf("inserire urgenza manutenzione:\n");
    scanf("%s",parola);
	fprintf(fp, "%s", stringaIntero(1,parola));
    printf("inserire costo manutenzione:\n");
    scanf("%s",parola);
	fprintf(fp, "%s", stringaIntero(2,parola));
    fprintf(fp, "%s", fine);

	fclose(fp);
}

void insUtil() {
    FILE *fp;
	char parola[200];
    char *prova_desc;
	fp = fopen("InserimentoDati.sql","a");
	fprintf(fp, "%s", inizio);
	fprintf(fp, "%s", "utstr");
	fprintf(fp, "%s", valori);
    printf("inserire data inizio utilizzo strumento (formato AAAA-MM-GG):\n");
    scanf("%s",parola);
	fprintf(fp, "%s", stringaIntero(1,parola));
    printf("inserire data fine utilizzo strumento (formato AAAA-MM-GG):\n");
    scanf("%s",parola);
	fprintf(fp, "%s", stringaIntero(1,parola));
    printf("inserire motivo utilizzo strumento:\n");
    scanf("%s",parola);
	fprintf(fp, "%s", stringaIntero(1,parola));
    printf("inserire codice responsabile utilizzo strumento:\n");
    scanf("%s",parola);
	fprintf(fp, "%s", stringaIntero(1,parola));
    printf("inserire codice strumento utilizzato:\n");
    scanf("%s",parola);
	fprintf(fp, "%s", stringaIntero(1,parola));
    printf("inserire nome dipartimento strumento utilizzato:\n");
    scanf("%s",parola);
	fprintf(fp, "%s", stringaIntero(3,parola));

    fprintf(fp, "%s", fine);

	fclose(fp);
}
