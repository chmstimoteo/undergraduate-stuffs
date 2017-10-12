char puzzle[4][4];



struct mov{
	char x,y;
};

mov R= {1,0};
mov L= {-1,0};
mov U= {0,-1};
mov D= {0,1};


void mover(char* x,char* y,mov mov){
	int temp;
	temp = puzzle[x][y];
	puzzle[x][y] = puzzle[x + mov.x][y + mov.y];
	puzzle[x + mov.x][y + mov.y] = temp;
	*x = x + mov.x;
	*y = y + mov.y;
}

mov oposto(mov mov){
	if(mov==U)return D;
	if(mov==D)return U;
	if(mov==L)return R;
	if(mov==R)return L;		
}

char pode(char x,char y,mov mov){

	if(x + mov.x < 0 || x + mov.x > 3 || y + mov.y < 0 || y + mov.y > 3){
		return 0;
	}
	return 1;
}


void partial_construct(char x,char y,mov last,mov candidates[],int* n,mov mov){

	char somax = last.x + mov.x;
	char somay = last.y + mov.y;
	
		if(pode(x,y,mov) && ( somax || somay)){
		  *n++;
		   candidates[*n] = mov;		
		}

}


void construct(char x,char y,mov last,mov candidates[],int* n){

	*n = 0;
	partial_construct(x,y,last,candidates,n,R);
	partial_construct(x,y,last,candidates,n,L);
	partial_construct(x,y,last,candidates,n,U);
	partial_construct(x,y,last,candidates,n,D);

}

char is_solution(int *steps){

	int x;
	if(*steps <= 50){
		for(x= 1 ; x < 16 ; x++){
			if(*(puzzle + x - 1) != x){
				return 0;
			}
		}
		return 1;	
	}
	return 0;

	
} 

char text[50];

void print(int* steps){
	printf("%c",text[0]);
	for(int i = 1 ; i < *steps ; i++){
		printf(" %c",text[i]);
	}
}

char solve(char x,char y,mov mov,mov last,mov candidates[],int* n,int* steps){

	char num_cand = *n;
	mov current_cand[4];

	

	if(is_solution(steps)){
		print(steps);
		return 1;
	}

	for(int i = 0;i < num_cand ; i++){
		current_cand[i] = candidates[i];
	}	
	
	for(int i = 0 ; i < num_cand ; i++){
		
		mover(x,y,current_cand[i]);
		*steps++;
		text[*steps] = 'a'; 

		construct(x,y,mov,candidates,n);
		if(solve(x,y,current_cand[i],mov,candidates,n,steps)){
			
			return 1;
		}
		
		mover(x,y,oposto(current_cand[i]));
		*steps--;
	}
}

int main(){
	char x,y;
	mov begin;
	begin.x = 10;
	mov candidates[4];
	int n =0;
	int steps = 0; 

	solve(x,y,begin,begin,candidates,&n,&steps);	
}
