/*
 * hawshv2.c
 * 
 * Copyright 2016  <pi@raspberrypi>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <wait.h>


//variablen
double version = 1.5;
char hostname[10];
char *username;
char pfad[1024];
int schleifeBeenden = 1;
//farben variablen
char *blau = "\033[1;34m";
char *neutral = "\033[0m";
char *gruen = "\033[1;32m";

//funktionsdefinitionen
void welcome();
void getWorkingPath();
void getHelp();
void callVersion();
int stringCompare(char s1[], char s2[]);
void getHelp();
int startsWith(char string[], char zeichen);
int endsWith(char string[], char zeichen);
void executeCommand(char string[]);
void statusline();

int main(int argc, char **argv)
{
	//die willkommensnachricht
	welcome();
	callVersion();
	
	printf("\n");
	char c[] = "\0";
	
	//die Hauptschleife die das Programm ausführt
	while (schleifeBeenden){
		
		//der aktuelle Pfad wird ermittelt
		getWorkingPath();
		
		c[0] = 0;
		
		//ist die zeile indem der username und der pad steht
		statusline();
		
		//es wird der Befehl eingelesen
		fgets(c, 30, stdin);
		
		//das enterzeichen wird abgeschnitten
		c[strlen(c)-1] = 0;
		
		//es wird die Eingabe abgefragt und zuerst
		//geprüft ob es build-in Befehle sind. Wenn nicht,
		//wird der Befehl an das System weitergegeben
		if(stringCompare(c, "quit")){
			return 112;
		}else if (stringCompare(c, "version")){
			callVersion();
		}else if (stringCompare(c, "help")){
			getHelp();
		}else if(startsWith(c, '/')){
			chdir(c);
		}else if(strlen(c)>=1){
			executeCommand(c);
		}
	}
	return 0;
}

//die willkommensnachricht die beim Start ausgegeben wird
void welcome(){
	printf("######HAWSH######\n");
	//printf("version: %f \n\n", version);
	gethostname(hostname, sizeof(hostname));
	username = getenv("USER");
	getWorkingPath();
}

//gibt den aktuellen arbeitspfad zurück (current working path)
void getWorkingPath(){
	getcwd(pfad, sizeof(pfad));	
}

//der Monolog "Version" 
void callVersion(){
		printf("HAW-Shell Version %.2f Autor: Patrick Hoeling, Mieke Narjes\n", version);
}

//vergleicht 2 Strings inhaltlich miteinander
//wenn sie gleich sind, wird 1 zurück gegeben, wenn nicht, 0
int stringCompare(char s1[], char s2[]){
	int i = 0;
	
	
	if(strcmp(s1,s2) == 0){
	
	while(s2[i] != 0){
		
		if(s1[i]!=s2[i]){
				return 0;
		}
		i=i+1;
	}
	}else{
		return 0;
	}
	return 1;
}
//der help monolog
void getHelp(){
	printf("\n");
	printf("---Hilfe---\n");
	printf("Built-in-Befehle:\n\n");
	printf("quit - Beendet die Shell\n");
	printf("version - Gibt die aktuelle Version und die Autoren der Shell\n");
	printf("help - Zeigt DIESEN Hifetext an\n");
	printf("/[Pfadname] - Wechselt in das angegebene Verzeichnis\n\n");

	
}
//prüft ob ein String mit einem bestimmten Zeichen beginnt
int startsWith(char string[], char zeichen){

	if(string[0]==zeichen){
		return 1;
	}
		return 0;
}

//prüft ob ein string mit einem bestimmten Zeichen endet
int endsWith(char string[], char zeichen){

	int letztesZeichen = strlen(string)-1;

	if(string[letztesZeichen]==zeichen){
	
	return 1;
	}
return 0;
}

//wenn der eingegebene String keinem built-in Befehl entspricht,
//wird der Befehl an die Shell übergeben
//mit der Option das der neue Prozess im Hintergrund laufen kann
void executeCommand(char string[]){
	
	int warteAufKind = endsWith(string, '&');
	
	if(endsWith(string, '&')){
		string[strlen(string)-1] = 0;
		
	}
	//wenn die neue Prozessid == 0, dann weiss der clon das er das kind ist
	pid_t pid;
	
	switch(pid = fork()){
			case -1: //hier ist ein fehler im fork
			break;
			
			case 0: //hier arbeitet der kindporozess
			
				schleifeBeenden = 0;
				printf("\n"); // neue Zeile
				
				execlp(string,string,NULL); // ersetzt momentanen Prozess mit neuem Input hier string (Parameter)
			break;
			
			default: //hier arbeitet der elternprozess
			
				if(warteAufKind == 1){
					
					if(waitpid(pid, NULL, 0) == -1){
						perror("waitpid() error:");
					}
				}
			break;
			
	
	}	
}

//gibt die Statusline aus
void statusline(){
	printf("%s%s: %s%s?>%s", blau, username, gruen, pfad, neutral);
}

