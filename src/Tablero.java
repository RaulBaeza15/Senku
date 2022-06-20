public class Tablero {
    public int[][] tablero ;
    private int numPiezas;
    public Tablero(){

        this.numPiezas=32;
        this.tablero = new int[7][7];
        tablero[0][0]=-1;tablero[0][1]=-1;tablero[0][2]=1;tablero[0][3]=1;tablero[0][4]=1;tablero[0][5]=-1;tablero[0][6]=-1;
        tablero[1][0]=-1;tablero[1][1]=-1;tablero[1][2]=1;tablero[1][3]=1;tablero[1][4]=1;tablero[1][5]=-1;tablero[1][6]=-1;
        tablero[2][0]=1;tablero[2][1]=1;tablero[2][2]=1;tablero[2][3]=1;tablero[2][4]=1;tablero[2][5]=1;tablero[2][6]=1;
        tablero[3][0]=1;tablero[3][1]=1;tablero[3][2]=1;tablero[3][3]=0;tablero[3][4]=1;tablero[3][5]=1;tablero[3][6]=1;
        tablero[4][0]=1;tablero[4][1]=1;tablero[4][2]=1;tablero[4][3]=1;tablero[4][4]=1;tablero[4][5]=1;tablero[4][6]=1;
        tablero[5][0]=-1;tablero[5][1]=-1;tablero[5][2]=1;tablero[5][3]=1;tablero[5][4]=1;tablero[5][5]=-1;tablero[5][6]=-1;
        tablero[6][0]=-1;tablero[6][1]=-1;tablero[6][2]=1;tablero[6][3]=1;tablero[6][4]=1;tablero[6][5]=-1;tablero[6][6]=-1;









    }

    public void setPieza(int i, int j, int pieza){

        if (i<7&&j<7)    tablero[i][j]=pieza;
    }
    public int getPieza(int i, int j){

       return tablero[i][j];
    }

    public void imprimirTablero(){
        String c;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < 7; j++) {
                if(tablero[i][j]==-1) c="\uD83E\uDD51";
                else if(tablero[i][j]==1)c="⚫";
                else c="⬜";
                System.out.print(c+" ");

            }
            System.out.println();
        }
        System.out.println();
    }
    public void resolverBT(){
        Booleano exito=new Booleano();
        anotarDesanotar(1, 3,3,1);
        resolverBTAux(exito);

    }
    public void resolverBTAux(Booleano exito){

        if(esSolucion()){
            exito.setValor(true);
        }else{

            int xNueva=0;
            int yNueva=0;
            int c ;
            while (xNueva< tablero.length&&!exito.getValor()){
                c=0;
                while(c<4&&!exito.getValor()&&tablero[xNueva][yNueva]==0){
                    if (aceptable( c, xNueva, yNueva)){

                        anotarDesanotar(c, xNueva,yNueva,1);
                        imprimirTablero();
                        resolverBTAux(exito);


                        if(!exito.getValor()){
                            anotarDesanotar(c,xNueva,yNueva,-1);
                        }
                    }
                    c++;
                }
                yNueva++;
                if(yNueva==tablero.length){
                    yNueva=0;
                    xNueva++;
                }
            }

        }


    }
    public void anotarDesanotarArriba(int x,int y,int sentido){
        if (sentido==1){
            tablero[x][y]=1;
            tablero[x][y-1]=0;
            tablero[x][y-2]=0;
            this.numPiezas--;

        }else{
            tablero[x][y]=0;
            tablero[x][y-1]=1;
            tablero[x][y-2]=1;
            this.numPiezas++;
        }

    }
    public void anotarDesanotarAbajo(int x,int y,int sentido){
        if (sentido==1){
            tablero[x][y]=1;
            tablero[x][y+1]=0;
            tablero[x][y+2]=0;
            this.numPiezas--;
        }else{
            tablero[x][y]=0;
            tablero[x][y+1]=1;
            tablero[x][y+2]=1;
            this.numPiezas++;
        }
    }
    public void anotarDesanotarDerecha(int x,int y,int sentido){
        if (sentido==1){
            tablero[x][y]=1;
            tablero[x+1][y]=0;
            tablero[x+2][y]=0;
            this.numPiezas--;
        }else{
            tablero[x][y]=0;
            tablero[x+1][y]=1;
            tablero[x+2][y]=1;
            this.numPiezas++;

        }
    }
    public void anotarDesanotarIzquierda(int x,int y,int sentido){
        if (sentido==1){
            tablero[x][y]=1;
            tablero[x-1][y]=0;
            tablero[x-2][y]=0;
            this.numPiezas--;
        }else{
            tablero[x][y]=0;
            tablero[x-1][y]=1;
            tablero[x-2][y]=1;
            this.numPiezas++;
        }

    }
    public void anotarDesanotar(int mov,int x,int y,int sentido){

        //mov =0 -> arriba,mov =1 -> abajo, mov =2 -> derecha,mov =3 -> izquierda

        if (mov==0){
            anotarDesanotarArriba( x, y, sentido);

        }else if(mov==1){

            anotarDesanotarAbajo( x, y, sentido);
        }else if (mov==2){

            anotarDesanotarDerecha( x, y, sentido);
        }else{
            anotarDesanotarIzquierda( x, y, sentido);

        }

    }
    public boolean aceptable(int mov,int x,int y){
        //mov =0 -> arriba,mov =1 -> abajo, mov =2 -> derecha,mov =3 -> izquierda
        boolean aceptable = false;
        if(numPiezas>15&&(tablero[1][2]+tablero[1][3] +tablero[1][4]==0||tablero[5][2]+tablero[5][3] +tablero[5][4]==0||tablero[2][1]+tablero[3][1] +tablero[4][1]==0||tablero[2][5]+tablero[3][5] +tablero[4][5]==0)){


        }else{
            if (mov==0&&y-2>=0&&tablero[x][y-2]==1&&tablero[x][y-1]==1){
                aceptable=true;
            }else if(mov==1&&y+2<7&&tablero[x][y+2]==1&&tablero[x][y+1]==1){
                aceptable=true;
            }else if (mov==2&&x+2<7&&tablero[x+2][y]==1&&tablero[x+1][y]==1){
                aceptable=true;
            }else if (mov==3&&x-2>=0&&tablero[x-2][y]==1&&tablero[x-1][y]==1){
                aceptable=true;
            }
        }

        return aceptable;
    }


    public boolean esSolucion() {

        boolean sol = false;
        sol = (this.numPiezas==1&&tablero[3][3] == 1);
        return sol;
    }
}
