package mod.simonsmod.core.util;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;


public class StateMatcher implements Predicate<IBlockState>
{
    private final IBlockState blockstate;

    private StateMatcher(IBlockState blockStateType)
    {
        this.blockstate = blockStateType;
    }

    public static StateMatcher forState(IBlockState blockState)
    {
        return new StateMatcher(blockState);
    }

    public boolean apply(@Nullable IBlockState p_apply_1_)
    {
        return p_apply_1_ != null && p_apply_1_ == this.blockstate;
    }
}