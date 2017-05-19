#include "md5_mesh.h"

#define IS_SPACE(c) (' ' == c || '\t' == c || '\r' ==c || '\n' == c )

void skipSpace(char* data){
    while (IS_SPACE(*data)){
        data++;
    }
}

void getToken(char* data,char* token){

}

void Md5_Read(char* data,long len){


}