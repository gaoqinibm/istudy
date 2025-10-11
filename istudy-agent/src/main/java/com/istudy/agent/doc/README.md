## Data Agent（数据智能体）
    Data Agent=大模型推理能力+数据处理引擎+业务知识图谱+自主决策机制
    模型上下文协议（Model Context Protocol，MCP）通过 MCP 协议，AI Agent 可以实现智能工具调用，根据运维场景自动选择和组合多个监控、诊断、治理工具，形成完整的运维治理工作流。
    MCP 协议支持上下文感知。

    利用Dify、Langchain、SpringAI等框架和平台
    基于Streamlit+LangChain+DashScope+DeepSeek的智能化数据分析助手

## Dify和Ollama
    两个开源工具，结合使用可实现本地部署AI知识库和大型语言模型管理。
    Dify‌：提供AI应用开发平台，支持低代码配置、模型管理及可观察性功能，可快速搭建从开发到生产的完整流程。
    Ollama‌：本地推理框架，可运行OpenAI gpt-oss、DeepSeek等开源语言模型，适用于文本生成、问答等场景。

    典型应用场景
    企业知识库：整合内部数据与外部模型，通过低代码工具快速构建AI应用。
    本地运算优化：避免依赖云端服务，降低延迟并增强数据隐私保护。

## 大模型能力
    大模型实现数据分析的技术途径基本还是以三种方式为主：自然语言转API、自然语言转SQL、以及自然语言转代码。

## 构建Data Agent三种基础技术方案
![Alt text](../doc/Data Agent的三种基础技术方案.png)

#### 自然语言转数据分析的API，text2API
![Alt text](../doc/Text2API.png)

#### 自然语言转关系数据库SQL，text2SQL-目前最受关注的一种大模型能力
![Alt text](../doc/Text2SQL.png)

#### 自然语言转数据分析的语言代码，即text2Code(即代码解释器方案)


基于 Apache Doris Data Agent 智能体平台全面架构如下图所示：
![Alt text](../doc/智能体架构图.png)

    Text2SQL（NL2sql）
    智能数据查询系统（NL2SQL）
    AIGC（Artificial Intelligence Generation Code）
    Chat2DB是一个AI原生的数据库客户端工具。你可以把它理解为：Navicat+大模型的结合体。
    Text2SQL（有时也被称为NL2SQL，即Natural Language to SQL）是一种技术或过程，它能够将自然语言的查询语句转换成结构化查询语言（SQL）的命令。
    
    Text2SQL开源项目 Chat2DB/DB-GPT/SQL Chat/SQLCoder/LLaMA C/DB-GPT
    Text2SQL的任务涵盖以下几个步骤：
    输入分析：用户以自然语言的形式输入问题，比如“找出平均工资高于整体平均工资的部门名称”。
    语义解析：系统对输入的自然语言问题进行解析，将其转化为数据库中的结构化查询语句。
    SQL生成：依据解析的结果，生成与之对应的SQL语句，例如“SELECT department_name FROM departments WHERE average_salary > (SELECT AVG(salary) FROM employees)” 。
    执行与反馈：系统执行SQL查询操作，并返回相应结果，同时可能会对结果进行进一步的解释或分析。
    
    AI+BI——“ChatBI”（智能问数系统）
