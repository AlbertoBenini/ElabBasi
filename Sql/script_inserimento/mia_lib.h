#ifndef MIA_H_
#define MIA_H_

union semun {
int val; /* for SETVAL */
struct semid_ds *buf; /* for IPC_STAT e IPC_SET */
unsigned short *array; /* per GETALL e SETALL */
struct seminfo  *__buf;
};

struct message {
  long mtype;
  char mtext[4];
};

char * intToChar(int num); ///Intero in stringa
int charToInt(char stringa[]);  ///Stringa in intero
int selCar(char a); ///Seleziona numero = carattere
char selLet(int a); ///Seleziona lettera = numero;
char * giraStringa(char stringa[]); ///Gira una stringa
char * concatStringa (char *p, char *s);    ///Concatena due stringhe senza controllo
float elevazione(int numero, int potenza); ///Eleva a potenza un numero
float creavirgola( char *c, int passi);  ///stringa al contrario, # caratteri in stringa ritorna parte decimale
float creanumero( char *c, int passi); ///stringa al contrario, # caratteri in stringa ritorna parte intera
int numero (char c); ///Numero data la lettera corrispondente
char * leggiConSpazi(); ///Leggo stringa con spazi fino ad "a capo"
void unlock(int semid, int semnum); ///Sblocca un semaforo
void lock(int semid, int semnum);      ///Blocca un semaforo
#endif
