## processor

Processor is the fundamental core architecture of streaming business systems.

#### Route: 路由

基于注解的路由

基于接口的路由

#### Processor：业务处理的流程

责任链模式：定义Event处理事件的顺序

策略模式：每个Event节点都路由到多态实现类，进行具体逻辑处理

观察者模式：每个Event节点都会抛出处理事件到EventBus，可定制Listener进行监听
