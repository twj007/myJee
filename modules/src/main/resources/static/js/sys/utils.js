function get_cookie(name){
    var cookies = window.document.cookie.split(";");
    for(var i = 0; i < cookies.length; i ++){
        var kv = cookies[i].split('=');
        if(kv && kv[0] === name){
            return kv[1];
        }
    }
}

function set_cookie(k, v){
    var expire = new Date().getTime() + 60 * 60 * 2;
    window.document.cookie = window.document.cookie ? window.document.cookie + ";" + k + "=" + v  + ';expires=' + expire : k + "=" + v + ';expires=' + expire;
}

function del_cookie(k){
    var cookies = window.document.cookie.split(';');
    var ck;
    for(var i = 0; i < cookies.length; i ++){
        var kv = cookies[i].split('=');
        if(kv && kv[0] != k){
            ck += cookies[i] + ';';
        }
    }
    window.document.cookie = ck.substr(0, ck.length - 2);
}