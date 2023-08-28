var cryptutil = require("./encrypt_ctr.js");


main();

function main(){
   var args = process.argv.slice(2);
   var option =  args[0];
   var input = args[1]
   var key = args[2];
   var optionUpper = option.toString().toUpperCase();
   console.log("The option is "+option+" the input:"+input);
   if("ENCRYPT"==optionUpper){
      var cipher =  cryptutil.encrypt(input,key);
      console.log("The cipher text :: "+cipher);
   }else if("DECRYPT"== optionUpper){
      var plaintext = cryptutil.decrypt(input,key);
      console.log("The plain text :: "+plaintext);
   }  
   
}
