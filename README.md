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

-----------------------------------------------------------------------------------------

## 整体模块设计与设计模式

#### 模块结构

系统主要由2个模块组成：

    +-------------------+  
    |     Processor     |  定义了业务处理的流程
    +-------------------+  
              +
    +-------------------+
    |    Implementor    |  定义了具体的业务逻辑实现
    +-------------------+ 

#### Processor：业务处理的流程

1. 责任链模式：定义Event处理事件的顺序
2. 策略模式：每个Event节点都路由到多态实现类，进行具体逻辑处理
3. 观察者模式：每个Event节点都会抛出处理事件到EventBus，可定制Listener进行监听

#### Implementor：具体的业务逻辑实现

1. 面向组合子编程：每个Implementor只应承载最细粒度的业务逻辑，我们希望单个Implementor仅实现业务上的"原子过程"，复杂的业务逻辑通过组合进行实现
2. 模板方法模式：Implementor的通用逻辑在Abstract中进行定义，每个业务场景可通过实现子类进行拓展

#### 如何组装以上两个模块？

1. 桥接模式：Processor负责组装Implementor，具体业务逻辑委托给Implementor
2. 组合+门面模式：Processor作为Implementor组合子的门面，对外提供模块整体的服务

-------------------------------------------------------------------------------      

## 业务逻辑层级结构设计

    +---+    +---------------------+       +---------------------+  抽象层 : 专注于顶层Context的通用逻辑实现
    | S |    |    SchemeContext    | <---> |         Core        |
    | E |    +---------------------+       +---------------------+  例如 : 定义基础Context结构和通用逻辑实现
    | N |    
    | D |    +---------------------+       +---------------------+  领域层 : 专注于跨Context的局部领域实现
    |   |    |    ContextDomain    | <---> |        Domain       |
    | T |    +---------------------+       +---------------------+  例如 : 定义部分Context同一领域的通用能力，定义局部通用实现
    | Y |    
    | P |    +---------------------+       +---------------------+  实现层 : 专注于具体Context的具体实现
    | E |    |   SpecificContext   | <---> |        Scheme       |
    +---+    +---------------------+       +---------------------+  例如 : 定义具体Context专属逻辑的实现，定义具体业务逻辑

#### Implementor 3层横切面

1. Core：业务场景无关的通用Context定义 + 业务场景无关的通用逻辑设计

2. Domain：跨部分业务场景Context定义 + 跨部分业务场景逻辑设计

3. Scheme：具体业务场景Context定义 + 具体业务场景逻辑设计

#### Implementor 1层垂直切面

1. SendType：在3层横切面中，都需要具备对不同SendType的处理能力。即SendType与场景无关，属于垂直切面

-------------------------------------------------------------------------------    

## Event处理链路设计

        +---------------+
        |    EXTERNAL   | 进入Processor处理链路之前的外部事件
        +---------------+
        
        +---------------+ 
        |     RECORD    | 记录源数据
        +---------------+
        
        +---------------+
        |      GRAY     | 对源数据进行灰度测试
        +---------------+
        
        +---------------+
        |     CHECK     | 对源数据进行检查
        +---------------+
        
        +---------------+
        |   ADJUSTMENT  | 关注点从源数据切换到上下文数据，需要对源数据进行调整，同时补充中间的过程数据
        +---------------+
        
        +---------------+
        |     FILTER    | 对上下文数据进行过滤
        +---------------+
        
        +---------------+
        |   SUPPLEMENT  | 补充业务数据到上下文
        +---------------+
        
        +---------------+
        |      MOCK     | 对将要发送的数据进行MOCK
        +---------------+
        
        +---------------+
        |      SEND     | 正式发送数据
        +---------------+

1. RECORD、GRAY、CHECK：关注源数据
2. ADJUSTMENT：关注点切换
3. FILTER、SUPPLEMENT、MOCK、SEND：关注上下文数据

-------------------------------------------------------------------------------      

## Context包结构设计

Context为Processor处理链路的上下文

#### 承载了3层横切面的能力

1. Core层：

   通过SchemeContext定义Core层能力，所有业务场景的Context都应具备该部分能力

2. Domain层：

   若该场景的Context具备某领域的能力，则该Context应该实现某领域的接口，来增强并获得该领域的能力

3. Scheme层：

   具体业务场景定制逻辑，在具体Scheme的Context中实现

#### 承载了1层垂直切面的能力

1. SendType层：

   通过实现SendType接口，使Context具备对应SendType的处理能力

#### 增强能力说明

1. Holdable

   Holdable提供了一个容器，用来承载Context的中间处理数据对象，提供了Lazy惰性求值的功能

### Contributing

[How to contribute](./CONTRIBUTING.md)
