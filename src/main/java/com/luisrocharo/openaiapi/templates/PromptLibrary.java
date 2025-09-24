package com.luisrocharo.openaiapi.templates;

import org.springframework.ai.chat.prompt.PromptTemplate;

public class PromptLibrary {

    public static final PromptTemplate CODE_TRANSLATOR_PROMPT_TEMPLATE =
            new PromptTemplate("""
                            Translate the following {sourceCode} code to {targetCode} , just give me the code:\n\n{code} ,
                            if the code snippet not match to {sourceCode} just answer with "Code snippet does not match the {sourceCode}"
                            """);
    
}
