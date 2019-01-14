import hashlib
import time
APP_KEY = "KJHSADFN11AZA"    #  申请到的APP_KEY
APP_SECRET = "SEECRECAFDSFD" #  申请到的APP_SECRET


def sortMap():
    # 1.按照key的字母顺序进行排序
    map = {"id":10000002,"centertype":0 }
    mapsort = sorted(map.keys() )
    # 2.map中参数的头部加上APP_SECRET
    stringBuffer =  ""
    for i in mapsort:
        stringBuffer +='%s%s' %(i,map[i])
    stringBuffer =APP_SECRET +  stringBuffer
    # 3. 步骤2中的字符串进行MD5加密
    m = hashlib.md5()
    m.update(stringBuffer.encode("utf8"))
    token = m.hexdigest()

    timestamp =  int(time.time())
    appkey = APP_KEY

    # 最终的 url
    url = "http://127.0.0.1:80/sso/api/v1/real/urls?id=10000000789&centertype=0&timestamp=#{timestamp}&appkey=#{appkey}&token=#{token}"

if __name__ == "__main__":
    sortMap()