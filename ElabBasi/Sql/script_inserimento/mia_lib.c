#include <stdio.h>
#include <stdlib.h>
#include<sys/sem.h>
#include "mia_lib.h"
#define KEYRAM 100
#define KEYSPR 555

struct lettera {
    char let;
    struct lettera *succ;
};

struct lettera *testa;

/**
 *@brief Funzione per convertire un intero in una stringa
 *@param La stringa da convertire
 *@return Il carattere corrispondente
 */
char * intToChar(int num) {
    int temp;
    int flag = 0;
    int rest = 0;
    int cifre=0;
    char *nuova;
    if(num==0) {
    nuova = (char *) malloc(sizeof(char)*2);
    nuova[0] = '0';
    nuova[1] = '\0';
    return nuova;
    }
    if(num<0) num = num - 2*num, flag=1;
    temp = num;
    while(temp>0) {
        rest = temp%10;
        temp = temp/10;
        cifre++;
    }
    if(flag==0) nuova = (char *) malloc(sizeof(char)*(cifre+1));
    else nuova = (char *) malloc(sizeof(char)*(cifre+2));
    int i=0;
    if(flag==1) cifre=cifre+1;
    for(i=0;i<cifre;i++) {
        if(flag==1) {
            nuova[i]='-';
            flag=0;
        }
        else {
        rest = num%10;
        num =num/10;
        nuova[i]=selLet(rest);
        }
    }
    nuova[cifre] = '\0';
    nuova = giraStringa(nuova);
    return nuova;
}
/**
 *@brief Funzione per convertire una stringa in intero
 *@param La stringa da convertire
 *@return Il numero corrispondente
 */
int charToInt(char stringa[]) {
    int d=1;
    int c=0;
    int totale=0;
    int cifra=0;
    int flag=0;
    stringa = giraStringa(stringa);
    while(stringa[c]!='\0') {
        if(stringa[c]=='-') flag++, c++;
        cifra=selCar(stringa[c]);
        if(cifra==-1) return -1;
        totale=totale+(cifra*d);
        d=d*10;
        c++;
    }

    if(flag==1) totale=totale*-1;
    return totale;
}

/**
 *@brief Funzione che converte carattere in intero
 *@param Il carattere da convertire
 *@return Il numero corrispondente
 */
int selCar(char a) {
    int ris=-1;
    if(a=='1') ris=1;
    if(a=='2') ris=2;
    if(a=='3') ris=3;
    if(a=='4') ris=4;
    if(a=='5') ris=5;
    if(a=='6') ris=6;
    if(a=='7') ris=7;
    if(a=='8') ris=8;
    if(a=='9') ris=9;
    if(a=='0') ris=0;
    return ris;
}

char selLet(int a) {
    int ris='z';
    if(a==1) ris='1';
    if(a==2) ris='2';
    if(a==3) ris='3';
    if(a==4) ris='4';
    if(a==5) ris='5';
    if(a==6) ris='6';
    if(a==7) ris='7';
    if(a==8) ris='8';
    if(a==9) ris='9';
    if(a==0) ris='0';
    return ris;
}

/**
 *@brief giraStringa
 *@param stringa da girare
 *@return stringa girata
 */
char * giraStringa(char stringa[]) {
    int c=0;
    int d=0;
    char *ritorna;
    while(stringa[c]!='\0') {
        c++;
    }
    char *nuova;
    nuova = (char *) malloc(sizeof(char)*c);
    d=c;
    c--;
    ritorna = nuova;
    if(stringa[0]=='-') {
        *nuova = '-';
        nuova++;
    }
    while(c>=0) {
        *nuova = stringa[c];
        nuova=nuova+sizeof(char);
        c--;
    }
    if(stringa[0]=='-') {
        nuova--;
        *nuova = '\0';
    }
    else *nuova = '\0';
    return ritorna;
}

/**
 *@brief concatStringa
 *@param stringa destinazione, stringa sorgente
 *@return stringa concatenata con indirizzo p
 */

char * concatStringa (char *p, char *s) {
    //non è controllato l'input!!!
    while(*p!='\0') {
        p++;
    }

    while(*s!='\0') {
        *p = *s;
        p++;
        s++;
    }

    p++;
    *p='\0';
}

/**
 *@brief Funzione per l'elevazione a potenza
 *@param base, potenza
 *@return numero elevato
  */
float elevazione(int numero, int potenza) {
	int i=0;
	float risultato = 1;
	if(potenza>0) {
		for(i=0;i<potenza;i++) {
			risultato = risultato * numero;
		}
	}
	else if(potenza<0){
	potenza = -potenza;
		for(i=1;i<=potenza;i++) {
			risultato = risultato / numero;
		}
	}
	return risultato;
}

/**
 *@brief Funzione per creare una parte decimale
 *@param stringa con il numero (a rovescio), potenza negativa
 *@return parte decimale
  */
float creavirgola( char *c, int passi) {
	float num=0;
	int cont=0,i=-1;
	cont = -(passi);
	for(i=(cont-1);i>=0;i--) {
		num = num + (numero(c[i])*elevazione(10,passi));
		passi++;
	}
	return num;
}

/**
 *@brief Funzione per creare una parte intera
 *@param stringa con il numero (a rovescio), potenza positiva
 *@return parte intera
  */
float creanumero( char *c, int passi) {
	float num=0;
	int i=0,cont=0;
	for(i=(passi-1);i>=0;i--) {
		num = num + (numero(c[i])*elevazione(10,cont));
		cont++;
	}
	return num;
}

/**
 *@brief Funzione per ritornare un numero data la lettera
 *@param il carattere
 *@return numero intero corrispondente
  */
int numero (char c) {
	switch(c) {
		case '0':
		return 0;
		break;
		case '1':
		return 1;
		break;
		case '2':
		return 2;
		break;
		case '3':
		return 3;
		break;
		case '4':
		return 4;
		break;
		case '5':
		return 5;
		break;
		case '6':
		return 6;
		break;
		case '7':
		return 7;
		break;
		case '8':
		return 8;
		break;
		case '9':
		return 9;
		break;
		default:
		return -1;
		break;
	}
}

/**
 *@brief Funzione per leggere una stringa con spazi
 *@param none
 *@return puntatore a stringa con spazi
  */

  char * leggiConSpazi() {
    char a;
    char *inizio;
    int c=0,i=0;
    testa = NULL;
    while(a!='\n') {
        a=getchar();
        struct lettera *nuovo;
        nuovo = (struct lettera *) malloc(sizeof(struct lettera));
        nuovo->let = a;
        nuovo->succ = testa;
        testa = nuovo;
        c++;
    }
    c++;
    inizio = malloc(sizeof(char)*c);
    struct lettera *p;
    p=testa;
    for(i=0;i<(c-1);i++) {
        inizio[i]=p->let;
        //free(p);
        p = p->succ;
    }
    inizio[c-1] = '\0';
    inizio = giraStringa(inizio);
    inizio[c-2] = '\0';
    return inizio;
  }


/**
 *@brief Funzione per bloccare un semaforo
 *@param semid semnum
  */
void lock(int semid, int semnum){
struct sembuf sops;
sops.sem_num=semnum;
sops.sem_op=-1;
sops.sem_flg=0;
semop(semid,&sops,1);
}
/**
 *@brief Funzione per sbloccare un semaforo
 *@param semid semnum
  */
void unlock(int semid, int semnum){
struct sembuf sops;
sops.sem_num=semnum;
sops.sem_op=1;
sops.sem_flg=0;
semop(semid,&sops,1);
}


