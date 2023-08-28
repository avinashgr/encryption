var crypto = require('crypto');


function encrypt(text,password){
  var key = Buffer.from(password, 'base64');
  var nonce = crypto.randomBytes(16);
  var cipher = crypto.createCipheriv('aes-256-ctr', key,nonce)
  const ciphertext = Buffer.concat([cipher.update(text), cipher.final()]);
  var buff =  Buffer.concat([nonce, ciphertext]);
  return buff.toString('base64');
}

function decrypt(text,password){
    var key = Buffer.from(password, 'base64');
    var ciphertextAndNonce = Buffer.from(text, 'base64');
    const nonce = ciphertextAndNonce.slice(0, 16);
    const ciphertext = ciphertextAndNonce.slice(16, ciphertextAndNonce.length);
    const cipher = crypto.createDecipheriv('aes-256-ctr', key, nonce);
    const decrypted = Buffer.concat([cipher.update(ciphertext), cipher.final()]);
    return decrypted.toString();
}


module.exports = { decrypt, encrypt };