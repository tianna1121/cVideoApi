##### 一、概述
本文是宜昌雪亮工程视频监控系统第三方接口说明文档，请按照文档说明进行接口开发，也可下载相对应语言的示例代码demo进行查看。
##### 二、接口说明
1. 申请
    首先向宜昌三峡云申请第三方接口账号和相对应的设备权限，获取到每个该账号对应的**APP_KEY**和**APP_SECRET**。接口中的参数将以APP_SECRET进行加密，请妥善保存该参数！若平台发现恶意接口访问，会将该第三方APP_KET进行封禁。
2. 接口文档 
    
    **1、获取设备列表**
    -  ***请求URL***
    > http://127.0.0.1:80/sso/api/v1/trees/client
    - **请求方式** 
    >**GET**
    -  **请求参数**
    
        | 参数名称 | 参数类型 | 参数说明 |
        | ------ | ------ | ------ |
        | appkey| string | 申请的appkey |
        | timestamp| long | 时间戳。表示1970年01月01日00时00分00秒起至现在的总秒数。单位 : 秒 |
        | token| string | 使用APP_SECRET签名算法加密后的字符串(见下方的签名算法) |
        - **返回**
        **当code=1时请求设备成功，其他均为失败。**

        ***onlineStatus : 0 表示不在线  1 表示在线***

        ***longitude : 经度***

        ***latitude  : 纬度***

        
      ```
          {
          	"code": 1,
          	"msg": "成功",
          	"data": {
          		"devices": [{
          			"id": 2113,
          			"deviceName": "龙潭河村广场",
          			"ip": null,
          			"parentId": 383,
          			"cloudControl": null,
          			"type": 1,
          			"netType": 1,
          			"onlineStatus": 1,
          			"recStatus": null,
                                "longitude" : 111.213797,
                                "latitude" :30.490161,
          			"saveType": 1
          		}, {
          			"id": 2112,
          			"deviceName": "龙潭河村服务大厅",
          			"ip": null,
          			"parentId": 383,
          			"cloudControl": null,
          			"type": 1,
          			"netType": 1,
          			"onlineStatus": 1,
          			"recStatus": null,
                                "longitude" : 111.213797,
                                "latitude" :30.490161,
          			"saveType": 1
          		}],
          		"devicegroups": [{
          			"id": 1,
          			"groupName": "宜都市",
          			"parentId": 0,
          			"orderNumber": 1,
          			"platformId": null,
          			"deviceId": null,
          			"type": 0,
          			"gbid": "42055100002000200001",
          			"outgbid": "42055100002000200001",
          			"children": null
          		}, {
          			"id": 2,
          			"groupName": "宜都市三峡云平台",
          			"parentId": 1,
          			"orderNumber": 1,
          			"platformId": null,
          			"deviceId": null,
          			"type": 0,
          			"gbid": "42055100002150000002",
          			"outgbid": "42055100002150000002",
          			"children": null
          		}, {
          			"id": 3,
          			"groupName": "一类视频监控",
          			"parentId": 2,
          			"orderNumber": 1,
          			"platformId": null,
          			"deviceId": null,
          			"type": 0,
          			"gbid": "42055100002150000003",
          			"outgbid": "42055100002160000003",
          			"children": null
          		}, {
          			"id": 4,
          			"groupName": "00雪亮工程",
          			"parentId": 3,
          			"orderNumber": 1,
          			"platformId": null,
          			"deviceId": null,
          			"type": 0,
          			"gbid": "42055100002150000004",
          			"outgbid": "42055100002160000004",
          			"children": null
          		}, {
          			"id": 383,
          			"groupName": "16wjf龙潭河村",
          			"parentId": 4,
          			"orderNumber": 17,
          			"platformId": null,
          			"deviceId": 10000000909,
          			"type": 1,
          			"gbid": "42055100002150000383",
          			"outgbid": "42055100002160000383",
          			"children": null
          		}]
          	}
          }
      ```
    
    **2、获取实时视频流**
    -  ***请求URL***
    > http://127.0.0.1:80/sso/api/v1/real/urls
    - **请求方式** 
    >**GET**
    -  **请求参数**

    | 参数名称 | 参数类型 | 参数说明 |
    | ------ | ------ | ------ |
    |id | long | 设备的ID |
    | centertype | int| 网路类型(视频专网=0 政务外网=1 互联网=2)  |
    | appkey| string | 申请的appkey |
    | timestamp| long | 时间戳。表示1970年01月01日00时00分00秒起至现在的总秒数。单位 : 秒 |
    | token| string | 使用APP_SECRET签名算法加密后的字符串(见下方的签名算法) |
    - **返回**
    **当code=1时请求设备成功，其他均为失败。**
    
  ```
      {
       "data": "rtmp://10.19.196.65:1554/liveonly/copy_100000025?token=GADFDFA",
       "code": 1,
       "msg": "操作正确"
      }
  ```

    

### 3、获取实时视频流(根据流类型返回对应码流)

    
    http://127.0.0.1:80/sso/api/v1/real/url/id
    请求方式
    GET
    请求参数
| 参数名称   | 参数类型 | 参数说明 |
| ---------- | -------- | -------- |
| id         | long     | 设备ID   |
| centertype | int      | 网路类型(视频专网=0 政务外网=1 互联网=2)      |
| rtptype    | string   | 视频流类型(rtsp=0 rtmp=1 hls=2)        |
| appkey     | string   | 申请的appkey       |
| timestamp  | long     | 时间戳。表示1970年01月01日00时00分00秒起至现在的总秒数。单位 : 秒       |
| token      | string   | 使用APP_SECRET签名算法加密后的字符串(见下方的签名算法)        |

    - **返回**
    **当code=1时请求设备成功，其他均为失败。**
    



#####三、 签名算法

#### 第一步：
按照请求参数名称将所有请求参数按照字母先后顺序排序得到：keyvaluekeyvalue...keyvalue  字符串。如：将id=100001,centertype=1 排序为：centertype=1, id=100001 然后将参数名和参数值进行拼接得到参数字符串：centertype1id100001 。

**特别注意以下重要规则：**
 * 参数名以字母先后顺序排序；
 * 若接口无参数，则不需要拼接，直接进行第二步 ;
 * appkey、timestamp和token不参与该字符串的拼接；
 * 参数名区分大小写；
 
#### 第二步：
将申请到的APP_SECRET加在参数字符串的头部后进行MD5加密 。即得到签名token。

>APP_SECRET是指你的API秘钥
注意不要泄露，妥善保存!


##### 举例：

假设申请到的APP_SECRET为"UYHGBVZDAD" ,假设传送的参数如下：
id:100000025
centertype:1
* 第一步：对参数按照key=value的格式，并按照参数名字母先后顺序排序如下：
                 stringA="centertype1id100000025";
* 第二步：拼接API密钥：

```
  stringSignTemp=UYHGBVZDAD+stringA
  即 stringSignTemp=UYHGBVZDADcentertype1id100000025
  token=  MD5(stringSignTemp)
  即token= MD5(UYHGBVZDADcentertype1id100000025)
```
* 第三步 : 加上token、app_key和timestamp 。请求http

 > http://127.0.0.1:80/sso/api/v1/real/urls?id=10000000789&centertype=0&timestamp=1547088082&appkey=APPKEY&token=cbb12e7a8f2360f003229607487946a9
