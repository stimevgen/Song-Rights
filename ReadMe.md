# Система хранения авторских прав

## Описание
Система для хранения авторских прав на песни. В дальнейшим эту систему будут использовать для лицензирования.
- У каждой песни есть исполнитель.
- На каждую песню может быть одно и несколько прав.
- Право имеет период действия.
- Право принадлежит какой-нибудь компании.
- За использования этого права компания взимает определенную сумму.
- Одна песня может иметь несколько авторских прав.

## Endpoints

Получить права на песню
```URL api/songrights/song/{songName} [GET] ```
```JSON
{
"title": "Frozen",
"singer": "Madonna",
"company": "Black Company",
"price": 8000
}
```

Получить все права на песни
```URL /api/songrights/ [GET] ```
```JSON
{
"id": 1,
"recording": {
"id": 1,
"title": "Frozen",
"version": "1",
"releaseTime": "1008-01-09T00:52:12+05:07:48",
"singer": {
"id": 1,
"name": "Madonna"
}
},
"company": {
"id": 1,
"name": "Black Company"
},
"price": 8000
}
```

Операцию для получения права за период
```URL /api/songrights/period?dateBegin={dateBegin}&dateEnd={dateEnd} [GET] ```
```JSON
{
"id": 1,
"recording": {
"id": 1,
"title": "Frozen",
"version": "1",
"releaseTime": "1008-01-09T00:52:12+05:07:48",
"singer": {
"id": 1,
"name": "Madonna"
}
},
"company": {
"id": 1,
"name": "Black Company"
},
"price": 8000
}
```

Получить всех прав одной организации
```URL /api/songrights/company/{companyNamne} [GET] ```
```JSON
{
"id": 1,
"recording": {
"id": 1,
"title": "Frozen",
"version": "1",
"releaseTime": "1008-01-09T00:52:12+05:07:48",
"singer": {
"id": 1,
"name": "Madonna"
}
},
"company": {
"id": 1,
"name": "Black Company"
},
"price": 8000
}
```

## Технологии   
Java 11, Spring, JPA, H2
"# Song-Rights" 
"# Song-Rights" 
