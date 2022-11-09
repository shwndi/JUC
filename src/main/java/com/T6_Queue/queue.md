Queue

| | | | |
|----|----|----|----|
|add|        增加一个元索                     |如果队列已满，则抛出一个IIIegaISlabEepeplian异常|
|remove|   移除并返回队列头部的元素    |如果队列为空，则抛出一个NoSuchElementException异常|
|element|  返回队列头部的元素             |如果队列为空，则抛出一个NoSuchElementException异常|
|offer|       添加一个元素并返回true       |如果队列已满，则返回false|
|poll|         移除并返问队列头部的元素    |如果队列为空，则返回null|
|peek|       返回队列头部的元素             |如果队列为空，则返回null|
|put|         添加一个元素                      |如果队列满，则阻塞|
|take|        移除并返回队列头部的元素     |如果队列为空，则阻塞|
|drainTo(list)|   一次性取出队列所有元素||


| | | 功能 | 特点 |
|----|----|----|----|
|add|offer|添加|添加报错||
|remove|poll|删除||
|element|peek|弹出||
|put|take|放取||||