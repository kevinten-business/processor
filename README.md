## processor

Processor is the fundamental core architecture of streaming business systems.

通过路由进行策略分发的流式处理链系统

### 使用场景介绍

定义Processor处理器链路，并通过Route对一个节点添加多个实现，

当请求进入后，根据请求对应的Route，将会进行对应链路的处理。

适用于处理链路相似的、但具有多种策略模式的流式业务系统。

### Route 路由策略方式

#### 基于注解的路由

Route: `io.processor.template.route.annotation.ProcessorRoute`

Processor: `io.processor.template.processor.TemplateAnnotationProcessor`

#### 基于接口的路由

Route: `io.processor.template.route.TemplateRouteKey`

Processor: `io.processor.template.processor.TemplateInterfaceProcessor`

### Processor 业务处理链使用的设计模式

责任链模式：定义Event处理事件的顺序

策略模式：每个Event节点都路由到多态实现类，进行具体逻辑处理

观察者模式：每个Event节点都会抛出处理事件到EventBus，可定制Listener进行监听

### Examples

`processor-template`中有基于 注解/接口 两种方式的样例

`processor-application-sample`中是一个MVC的示例程序

### Contributing

[How to contribute](./CONTRIBUTING.md)
