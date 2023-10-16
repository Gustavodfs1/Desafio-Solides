# Desafio Solides :)

### Para Subir a API é necessário Docker instalado na maquina e rodando
O arquivo dockerCompose.yml irá baixar uma imagem e subir um container do banco Postgrees, necessário para aplicação.
Somente é necessário rodar a aplicação no IntelliJ normalmente ou via linha de comando como uma aplicação spring boot comum.

### Funcionamento da API:
##### SWAGGER http://localhost:8080/swagger-ui/index.html
O endpoint /user/register é utilizado para registro de novos usuarios e retorna um token JWT para autorização das demais requests.
Os relacionamentos das entidades estão refletidos na url, como exemplo:
photo estão em um album logo a url resultante: POST album/id_do_album/photo.
Tambem os comentários são de um post sendo a url POST post/id_do_post/comment, uma particularidade dos comentarios
que tem os mesmos atributos de um post utilizei um auto referênciamento do post para designar um comentário.
Somente os donos dos albuns e fotos podem deletar os mesmos.
Optei por não permitir a deleção de um album que contenha fotos dentro dele, poderia usar também um cascade delete mas lançei uma exceção informando que ainda há fotos neste album.
Optei por não utilizar roles e @PreAuthorize, portanto fiz uma verificação no banco do owner do recurso para autorizar ou não sua deleção.
Optei por não utilizar map struct pela simplicidade dos objetos.
Gerenciamento do banco por Liquibase.


Testes unitários dos services e teste integrado com test container utilizando imagem Docker do postgres, para fidelidade do teste com o mesmo banco de produção.

Todos os endpoints (exceto os /user) requer token de acesso.
